/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.goal;

import java.util.List;
import java.util.Map;

/**
 *
 * @author genesis
 * scope = /link/Cat/ /Dog/ /friend
 * goal[1]: 
 *      /to/node/name=bark
 *      /to/node/id=5
 * goal[2]: 
 *      /to/node/name=another
 *      /to/node/id=6
 */
public class ScopeGoal {

    public final String scopeURI;
    public final Map<String, String> goalValues;

    public ScopeGoal(String scopeURI, Map<String, String> goalValues) {
        this.scopeURI = scopeURI;
        this.goalValues = goalValues;
    }

}
