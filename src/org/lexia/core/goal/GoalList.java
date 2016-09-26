/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.goal;

import java.util.List;
import org.lexia.core.goal.Goal;

/**
 *
 * @author genesis
 */
public class GoalList {
    public final Goal metaGoal;
    public final List<Goal> goals;
    public GoalList(Goal metaGoal,List<Goal> goals) {
        this.metaGoal = metaGoal;
        this.goals = goals;
    }
}
