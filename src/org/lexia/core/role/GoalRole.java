/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.role;

import java.util.Objects;
import org.lexia.core.format.MemberListFormat;
import org.lexia.core.goal.Goal;

/**
 *
 * @author genesis
 */
public class GoalRole {
    public static final String GOAL_ROLE = "GoalRole";

    public final RoleName roleName;
    public final Goal goal;
    public GoalRole(RoleName roleName, Goal goal) {
        this.roleName = roleName;
        this.goal = goal;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.goal);
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
        final GoalRole other = (GoalRole) obj;
        if (this.roleName != other.roleName) {
            return false;
        }
        if (!Objects.equals(this.goal, other.goal)) {
            return false;
        }
        return true;
    }
  
    protected final String toURI(MemberListFormat listFormat) {
        StringBuilder builder = new StringBuilder();
        builder.append(GOAL_ROLE).append(listFormat.memberFormat.is);
        appendToURI(builder, listFormat);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberListFormat listFormat) {
        builder.append(listFormat.memberFormat.open);
        this.roleName.appendToURI(builder, listFormat.memberFormat.attributeFormat);
        builder.append(listFormat.memberFormat.and);
        this.goal.appendToURI(builder, listFormat);
        builder.append(listFormat.memberFormat.close);
    }

    private static GoalRole instance(RoleName roleName, Goal goal) {
        return new GoalRole(roleName, goal);
    }

    public static final GoalRole FROM(Goal goal) {
        return GoalRole.instance(RoleName.FROM, goal);
    }
    
    public static final GoalRole TO(Goal goal) {
        return GoalRole.instance(RoleName.TO, goal);
    }
    
    public static final GoalRole PREVIOUS(Goal goal) {
        return GoalRole.instance(RoleName.PREVIOUS, goal);
    }

    public static final GoalRole AS(Goal goal) {
        return GoalRole.instance(RoleName.WITH, goal);
    }
}
