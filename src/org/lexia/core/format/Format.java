/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.format;

import org.lexia.core.Oops;
import org.lexia.core.SayWhat;

/**
 *
 * @author genesis /name/value --> "/","/","" name=value --> "","=",""
 */
public abstract class Format extends BasicFormat {

    public final String open;
    public final String and;
    public final String close;

    protected Format(String open, String is, Quote quote, String and, String close) {
        super(is, quote);
        this.open = open;
        this.and = and;
        this.close = close;
    }

    public String stripUpToOpenIncluded(StringBuilder uri) {
        return super.stripUpToFirstIncluding(uri, open, false);
    }

    public String stripUpToAndIncluded(StringBuilder uri) {
        return super.stripUpToFirstIncluding(uri, and, true);
    }

    public String stripUpToCloseOnlyIncluded(StringBuilder uri) {
        //if (close.length() > 0) {
        //    return this.stripUpToFirstIncluding(uri, close, false);
        //} else {
        if (and.length() > 0) {
            int nextAnd = uri.indexOf(and);
            int nextClose = uri.indexOf(close);
            if ((nextAnd > -1) && ((nextClose < 1) || (nextAnd < nextClose))) {
                return super.stripUpToFirstExcluding(uri, and, false);
            } else {
                if (close.length() > 0) {
                    return super.stripUpToFirstExcluding(uri, close, false);
                } else {
                    return this.trim(uri.toString());
                    //throw new Oops(close);
                }
            }
        } else {
            if (close.length() > 0) {
                return super.stripUpToFirstExcluding(uri, close, false);
            } else {
                throw new Oops(close);
            }
        }
        //}
    }
    public final String stripOpenIncluding(StringBuilder uri) {
        int firstUpTo = uri.indexOf(this.open);
        if (firstUpTo > -1) {
            String value = uri.substring(0, firstUpTo);
            uri.delete(0, firstUpTo + this.open.length());
            uri.trimToSize();
            if (value.length() > 0) {
                throw new Oops(value);
            } else {
                return value;
            }
        } else {
            throw new SayWhat(this.open, uri);
        }
    }

    public final String stripAndIncluding(StringBuilder uri) {
        int firstUpTo = uri.indexOf(this.and);
        if (firstUpTo > -1) {
            String value = uri.substring(0, firstUpTo);
            uri.delete(0, firstUpTo + this.and.length());
            uri.trimToSize();
            if (value.length() > 0) {
                throw new Oops(value);
            } else {
                return value;
            }
        } else {
            throw new SayWhat(this.and, uri);
        }
    }

    public final String stripCloseIncluding(StringBuilder uri) {
        int firstUpTo = uri.indexOf(this.close);
        if (firstUpTo > -1) {
            String value = uri.substring(0, firstUpTo);
            uri.delete(0, firstUpTo + this.close.length());
            uri.trimToSize();
            if (value.length() > 0) {
                throw new Oops(value);
            } else {
                return value;
            }
        } else {
            throw new SayWhat(this.close, uri);
        }
    }

    public Label stripLabelUpToIsOpenIncluded(StringBuilder uri) {
        if (is.length() > 0) {
            Label label = this.stripLabelUpToIsIncluded(uri);
            if (open.length() > 0) {
                this.stripOpenIncluding(uri);
            }
            return label;
        } else {
            if (open.length() > 0) {
                String value = this.stripUpToOpenIncluded(uri);
                return new Label(value);
            } else {
                throw new SayWhat(is, uri);
            }
        }
    }

    @Override
    protected String format(String value) {
        value = super.format(value);
        if ((open.length() > 0) && (value.contains(open))) {
            throw new Oops(value);
        }
        if ((and.length() > 0) && (value.contains(and))) {
            throw new Oops(value);
        }
        if ((close.length() > 0) && (value.contains(close))) {
            throw new Oops(value);
        }
        return value;
    }

}
