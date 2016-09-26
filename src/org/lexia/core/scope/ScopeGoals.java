/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.scope;

import org.lexia.core.goal.GoalList;

/**
 *
 * @author genesis
 */
public class ScopeGoals {
    public final Scope scope;
    public final GoalList goalList;
    public ScopeGoals(Scope scope,GoalList goalList) {
        this.scope = scope;
        this.goalList = goalList;
    }
    
}
