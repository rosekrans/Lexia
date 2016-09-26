/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.legend;

import java.util.Objects;
import org.lexia.core.format.Label;
import org.lexia.core.format.MemberFormat;

/**
 *
 * @author genesis
 */
public class LegendName {

    public static final Label LEGEND_NAME = new Label("legend_name");
    public final String name;

    protected LegendName(String name) {
        this.name = name;
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LegendName other = (LegendName) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    protected static LegendName instance(String name) {
        return new LegendName(name);
    }

    public static LegendName For(String name) {
        return instance(name);
    }

    public final String toURI(MemberFormat format) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, format);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberFormat format) {
        format.appendLabel(builder, LEGEND_NAME);
        builder.append(format.attributeFormat.is);
        format.appendValue(builder,this.name);
    }
}
