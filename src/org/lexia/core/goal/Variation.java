/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.goal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.lexia.core.format.Label;
import org.lexia.core.format.MemberListFormat;
import org.lexia.core.kind.Shape;
import org.lexia.core.role.GoalRole;
import org.lexia.core.role.KindRole;

/**
 *
 * @author genesis
 */
public class Variation {
    public static final Label VARIATION  = new Label("Variation");

    public final Map<KindRole, GoalRole> goalRoles;
    public final Shape shape;

    public Variation(Shape shape) {
        this(shape,new HashSet<GoalRole>());
    }

    public Variation(Shape shape, Set<GoalRole> goalRoles) {
        this.shape = shape;
        Map<KindRole, GoalRole> map = new HashMap<>();
        Set<KindRole> kindRoles = this.shape.kindRoles;
        for (KindRole kindRole : kindRoles) {
            for (GoalRole goalRole : goalRoles) {
                if (kindRole.roleName.name.equals(goalRole.roleName.name)) {
                    if (kindRole.kind.kindName.name.equals(goalRole.goal.kind.kindName.name)) {
                        map.put(kindRole, goalRole);
                    }
                }
            }
        }
        this.goalRoles = map;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.goalRoles);
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
        final Variation other = (Variation) obj;
        if (!Objects.equals(this.goalRoles, other.goalRoles)) {
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
        format.appendLabel(builder, VARIATION);
        builder.append(format.is);
        if (goalRoles.size() > 0) {
            builder.append(format.open);
            String and = "";
            for (GoalRole goalRole : this.goalRoles.values()) {
                builder.append(and);
                goalRole.appendToURI(builder, format);
                and = format.and;
            }
            builder.append(format.close);
        } else {
                builder.append(format.open);
                builder.append(format.close);
        }
    }

}
