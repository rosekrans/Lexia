/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.goal;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.lexia.core.format.Label;
import org.lexia.core.format.MemberListFormat;
import org.lexia.core.kind.Kind;
import org.lexia.core.kind.Shape;
import org.lexia.core.scope.ScopeGoals;
import org.lexia.core.symbol.Symbol;
import org.lexia.core.value.Value;

/**
 *
 * @author genesis
 */
public class Goal {
    public static final Label GOAL = new Label("Goal");
    //public final boolean isUnique;
    public final Kind kind;
    public final Long id;
    public final Set<Value> aspectValues;
    public final Variation variation;
    public final Symbol identitySymbol;
    
    private List<ScopeGoal> scopeGoals;
    private List<ScopeGoals> scopeGoalsList;
    private Object subject;

    protected Goal(Kind kind,Long id, Set<Value> aspectValues, Variation variation, Symbol identitySymbol) {
        this.kind = kind;
        this.id = id;
        this.aspectValues = aspectValues;
        this.variation = variation;
        this.identitySymbol = identitySymbol;
    }

    public List<ScopeGoal> getScopeGoals() {
        return scopeGoals;
    }

    public void setScopeGoals(List<ScopeGoal> scopeGoals) {
        this.scopeGoals = scopeGoals;
    }

    public List<ScopeGoals> getScopeGoalsList() {
        return scopeGoalsList;
    }

    public void setScopeGoalsList(List<ScopeGoals> scopeGoalsList) {
        this.scopeGoalsList = scopeGoalsList;
    }

    public Object getSubject() {
        return subject;
    }

    public void setSubject(Object subject) {
        this.subject = subject;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.kind);
        //hash = 67 * hash + Objects.hashCode(this.aspectValues);
        hash = 67 * hash + Objects.hashCode(this.variation);
        //hash = 67 * hash + Objects.hashCode(this.identitySymbol);
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
        final Goal other = (Goal) obj;
        if (!Objects.equals(this.kind, other.kind)) {
            return false;
        }
        /*if (!Objects.equals(this.aspectValues, other.aspectValues)) {
         return false;
         }*/
        if (!Objects.equals(this.variation, other.variation)) {
            return false;
        }
        if (!Objects.equals(this.identitySymbol, other.identitySymbol)) {
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
        listFormat.appendLabel(builder, GOAL);
        builder.append(listFormat.memberFormat.is).append(listFormat.memberFormat.open);
        this.kind.kindName.appendToURI(builder, listFormat.memberFormat);
        builder.append(listFormat.memberFormat.and);
        this.kind.domainSet.appendToURI(builder, listFormat.memberFormat.attributeFormat);
        builder.append(listFormat.memberFormat.and);
        this.variation.appendToURI(builder, listFormat);
        builder.append(listFormat.memberFormat.and);
        if (this.identitySymbol != null) {
            this.identitySymbol.appendToURI(builder, listFormat);
        } else {
            listFormat.appendLabel(builder, Symbol.SYMBOL);
            builder.append(listFormat.memberFormat.is);
            listFormat.appendValue(builder, null);
        }
        builder.append(listFormat.memberFormat.close);
    }
    
    public static Goal shapedAndHandledOrNamed(Kind kind,Long id, Set<Value> aspectValues, Variation variation, Symbol identityValue) {
        return new Goal(kind,id, aspectValues, variation, identityValue);
    }

    protected static Goal named(Kind kind,Long id, Set<Value> aspectValues, Symbol identityValue) {
        return new Goal(kind,id, aspectValues, new Variation(new Shape()), identityValue);
    }

    protected static Goal shaped(Kind kind,Long id, Set<Value> aspectValues, Variation variation) {
        return new Goal(kind,id, aspectValues, variation, null);
    }

    protected static Goal namedAndShaped(Kind kind,Long id, Set<Value> aspectValues, Variation variation, Symbol identityValue) {
        return new Goal(kind,id, aspectValues, variation, identityValue);
    }
}
