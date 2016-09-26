/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.legend;

import java.util.Objects;
import org.lexia.core.attribute.Attribute;
import org.lexia.core.format.Label;
import org.lexia.core.format.MemberListFormat;

/**
 *
 * @author genesis
 */
public class Legend {

    public static final Label LEGEND = new Label("Legend");
    public static final Label IDENTITY = new Label("Identity");

    public final LegendName legendName;
    public final Attribute identityAttribute;

    protected Legend(LegendName name, Attribute identityAttribute) {
        this.legendName = name;
        this.identityAttribute = identityAttribute;
    }

    public boolean unique() {
        return this.equals(this);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.legendName);
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
        final Legend other = (Legend) obj;
        if (!Objects.equals(this.legendName, other.legendName)) {
            return false;
        }
        if (!Objects.equals(this.identityAttribute, other.identityAttribute)) {
            return false;
        }
        return true;
    }

    public final String toURI(MemberListFormat format,Label label) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, format,label);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberListFormat listFormat,Label label) {
        listFormat.appendLabel(builder, label);
        builder.append(listFormat.memberFormat.is).append(listFormat.memberFormat.open);
        this.legendName.appendToURI(builder, listFormat.memberFormat);
        builder.append(listFormat.memberFormat.and);
        if (this.identityAttribute != null) {
            this.identityAttribute.appendToURI(builder, listFormat.memberFormat,Attribute.ATTRIBUTE);
        } else {
            listFormat.appendLabel(builder, Attribute.ATTRIBUTE);
            builder.append(listFormat.memberFormat.is).append("null");
        }
        builder.append(listFormat.memberFormat.close);
    }


    protected static final Legend instance(LegendName legendName, Attribute identityAttribute) {
        return new Legend(legendName, identityAttribute);
    }

    protected static final Legend named(LegendName legendName, Attribute identityAttribute) {
        return new Legend(legendName, identityAttribute);
    }

}
