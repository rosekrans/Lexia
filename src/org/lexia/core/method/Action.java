/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.method;

import java.util.List;
import org.lexia.core.format.MemberListFormat;
import org.lexia.core.goal.Goal;
import org.lexia.core.value.Value;

/**
 *
 * @author genesis
 */
public class Action {

    public static final String ACTION = "Action";
    public final Goal goal;
    public final Reach reach;

    private Constraint constraint;
    private List<ExpansionGoal> expansionGoals; 

    protected Action(Goal goal, Reach reach) {
        this.goal = goal;
        this.reach = reach;
    }

    public Constraint getConstraint() {
        return constraint;
    }

    public void setConstraint(Constraint constraint) {
        this.constraint = constraint;
    }

    public List<ExpansionGoal> getExpansionGoals() {
        return expansionGoals;
    }

    public void setExpansionGoals(List<ExpansionGoal> expansionGoals) {
        this.expansionGoals = expansionGoals;
    }

    public final String toURI(MemberListFormat format) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, format);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberListFormat listFormat) {
        builder.append(ACTION).append(listFormat.memberFormat.is).append(listFormat.memberFormat.open);
        this.goal.kind.kindName.appendToURI(builder, listFormat.memberFormat);
        builder.append(listFormat.memberFormat.and);
        this.reach.appendToURI(builder, listFormat);
        builder.append(listFormat.memberFormat.and);
        if (this.goal.identitySymbol != null) {
            this.goal.identitySymbol.appendToURI(builder, listFormat);
        } else {
            listFormat.appendLabel(builder, Value.VALUE);
            builder.append(listFormat.memberFormat.is);
            listFormat.appendValue(builder, null);
        }
        builder.append(listFormat.memberFormat.close);
    }

    protected static final Action instance(Goal goal, Reach reach) {
        return new Action(goal, reach);
    }

}
