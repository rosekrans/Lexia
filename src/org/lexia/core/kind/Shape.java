/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.kind;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.lexia.core.format.Label;
import org.lexia.core.format.MemberListFormat;
import org.lexia.core.role.KindRole;

/**
 *
 * @author genesis
 */
public class Shape {

    public static final Label SHAPE = new Label("Shape");

    public final Set<KindRole> kindRoles;

    public Shape() {
        this(new HashSet<KindRole>());
    }

    public Shape(Set<KindRole> kindRoles) {
        this.kindRoles = kindRoles;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.kindRoles);
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
        final Shape other = (Shape) obj;
        if (!Objects.equals(this.kindRoles, other.kindRoles)) {
            return false;
        }
        return true;
    }

    public final String toURI(MemberListFormat format) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, format);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberListFormat format) {
        format.appendLabel(builder,SHAPE);
        builder.append(format.is);
        if (kindRoles.size() > 0) {
            builder.append(format.open);
            String and = "";
            for (KindRole kindRole : kindRoles) {
                builder.append(and);
                kindRole.appendToURI(builder, format);
                and = format.and;
            }
            builder.append(format.close);
        } else {
            if (format.open.length() > 0) {
                builder.append(format.open);
                builder.append(format.close);
            } else {
                //builder.append("empty");
            }
        }
    }
}
