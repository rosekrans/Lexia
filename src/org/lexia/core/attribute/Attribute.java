/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.attribute;

import java.util.Objects;
import org.lexia.core.SayWhat;
import org.lexia.core.format.Label;
import org.lexia.core.format.AttributeFormat;
import org.lexia.core.format.MemberFormat;

/**
 *
 * @author genesis
 */
public class Attribute implements Comparable<Object>{

    public static final Label ATTRIBUTE = new Label("Attribute");
    public static final Label NAME = new Label("Name");

    public final String name;

    protected Attribute(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Attribute other = (Attribute) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public final String toURI(MemberFormat format) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, format,ATTRIBUTE);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberFormat format,Label label) {
        format.appendLabel(builder, label);
        builder.append(format.is).append(format.open);
        appendStateToURI(builder, format.attributeFormat, NAME);
        //if (format.open.length() > 0) {
        builder.append(format.close);
        //}
    }

    public final void appendStateToURI(StringBuilder builder, AttributeFormat format, Label label) {
        format.appendLabel(builder, label);
        builder.append(format.is);
        format.appendValue(builder, this.name);
    }
    
    public static final Attribute instance(String value) {
        return new Attribute(value);
    }

    public static final Attribute ToAttribute(MemberFormat format, String uri) {
        return ToAttribute(format, new StringBuilder(uri));
    }

    public static final Attribute ToAttribute(MemberFormat format, StringBuilder uri) {
        //if (format.open.length() > 0) {
        Label type = format.stripLabelUpToIsOpenIncluded(uri);
        if (ATTRIBUTE.equals(type)) {
            return ToAttributeFromState(format, uri, NAME);
        } else {
            throw new SayWhat(type.value, uri);
        }
        //}
    }

    public static final Attribute ToAttributeFromState(MemberFormat format, StringBuilder uri, Label label) {
        String value = null;
        //System.out.println("ToAttribute:"+ATTRIBUTE+":"+type.value+", "+uri.toString());
        Label name = format.attributeFormat.stripLabelUpToIsIncluded(uri);
        if (label.equals(name)) {
            //System.out.println("ToAttribute:"+NAME+":"+name.value+", "+uri.toString());
            if (format.close.length() > 0) {
                value = format.stripUpToCloseOnlyIncluded(uri);
            } else {
                value = uri.toString();
            }
            //System.out.println("ToAttribute:value:"+value+", "+uri.toString());
        } else {
            throw new SayWhat(name.value, uri);
        }
        return new Attribute(value);
    }

}
