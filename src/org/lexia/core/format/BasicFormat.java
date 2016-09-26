/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.format;

import org.lexia.core.OhNo;
import org.lexia.core.Oops;
import org.lexia.core.SayWhat;

/**
 *
 * @author genesis
 */
public abstract class BasicFormat {

    public static class Quote {

        public final String start;
        public final String end;

        public Quote(String start, String end) {
            this.start = start;
            this.end = end;
        }
    }

    public final String is;
    public final Quote quote;

    public BasicFormat(String is, Quote quote) {
        this.is = is;
        this.quote = quote;
    }

    protected String format(String value) {
        if (value != null) {
            if ((quote.start.length() > 0) && (quote.end.length() > 0)) {
                if (value.contains(quote.start)) {
                    throw new Oops(value);
                }
                if (value.contains(quote.end)) {
                    throw new Oops(value);
                }
                return value;
            } else {
                if ((is.length() > 0) && (value.contains(is))) {
                    throw new Oops(value);
                }
                if ((quote.start.length() > 0) && (value.contains(quote.start))) {
                    throw new Oops(value);
                }
                if ((quote.end.length() > 0) && (value.contains(quote.end))) {
                    throw new Oops(value);
                }
                return value;
            }
        } else {
            throw new Oops(value);
        }
    }

    protected final String trim(String value) {
        value = value.trim();
        if ("null".equals(value)) {
            return value;
        } else {
            if ((value.startsWith(quote.start)) && (value.endsWith(quote.end))) {
                return value.substring(quote.start.length(), value.length() - quote.end.length());
            } else {
                throw new Oops(value);
            }
        }
    }

    protected final String stripUpToFirstIncluding(StringBuilder uri, String upTo, boolean returnNull) {
        int firstUpTo = uri.indexOf(upTo);
        if (firstUpTo > -1) {
            String value = uri.substring(0, firstUpTo);
            uri.delete(0, firstUpTo + upTo.length());
            uri.trimToSize();
            value = this.trim(value);
            //System.out.println("VALUE:"+value.toString());
            if ("null".equals(value)) {
                return null;
            } else {
                return value;
            }
        } else {
            if (returnNull) {
                return null;
            } else {
                throw new SayWhat(upTo, uri);
            }
        }
    }

    protected final String stripUpToFirstExcluding(StringBuilder uri, String upTo, boolean returnNull) {
        int firstUpTo = uri.indexOf(upTo);
        if (firstUpTo > -1) {
            String value = uri.substring(0, firstUpTo);
            uri.delete(0, firstUpTo);
            value = this.trim(value);
            //System.out.println("VALUE:"+value.toString());
            if ("null".equals(value)) {
                return null;
            } else {
                return value;
            }
        } else {
            if (returnNull) {
                return null;
            } else {
                throw new SayWhat(upTo, uri);
            }
        }
    }

    public final Label stripLabelUpToIsIncluded(StringBuilder uri) {
        return new Label(this.stripUpToFirstIncluding(uri, is, false));
    }

    public final void appendValue(StringBuilder builder, String value) {
        if (value != null) {
            value = this.format(value);
            builder.append(this.quote.start).append(value).append(this.quote.end);
        } else {
            builder.append(value);
        }
    }

    public final void appendLabel(StringBuilder builder, Label label) {
        if (label != null) {
            appendValue(builder, label.value);
        } else {
            throw new Oops(OhNo.NULL_NOT_ALLOWED);
        }

    }
}
