/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.symbol;

import java.util.Objects;
import org.lexia.core.format.Label;
import org.lexia.core.format.MemberListFormat;
import org.lexia.core.legend.Legend;
import org.lexia.core.value.Value;

/**
 *
 * @author genesis
 */
public class Symbol {

    public static final Label SYMBOL = new Label("symbol");
    public final Legend legend;
    public final Value identityValue;

    protected Symbol(Legend legend, Value identityValue) {
        this.legend = legend;
        this.identityValue = identityValue;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.legend);
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
        final Symbol other = (Symbol) obj;
        if (!Objects.equals(this.legend, other.legend)) {
            return false;
        }
        if (!Objects.equals(this.identityValue, other.identityValue)) {
            return false;
        }
        return true;
    }

    public final String toURI(MemberListFormat format) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, format);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberListFormat listFormat) {
        listFormat.appendLabel(builder, SYMBOL);
        builder.append(listFormat.memberFormat.is).append(listFormat.memberFormat.open);
        this.legend.legendName.appendToURI(builder, listFormat.memberFormat);
        builder.append(listFormat.memberFormat.and);
        if (this.identityValue != null) {
            this.identityValue.appendToURI(builder, listFormat.memberFormat);
        } else {
            listFormat.appendLabel(builder, Value.VALUE);
            builder.append(listFormat.memberFormat.is);
            listFormat.appendValue(builder, null);
        }
        builder.append(listFormat.memberFormat.close);
    }

    protected static Symbol named(Legend legend, Value identityValue) {
        return new Symbol(legend, identityValue);
    }

}
