/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.method;


/**
 *
 * @author genesis
 */
public class ExpansionGoal {
    public final Action action;
    public final Boolean ascending;
    public final int anchor;

    public ExpansionGoal(Action action, Boolean ascending,int anchor) {
        this.action = action;
        this.ascending = ascending;
        this.anchor = anchor;
    }
}
