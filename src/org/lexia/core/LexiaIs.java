/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core;

import org.lexia.core.goal.Goal;
import org.lexia.core.scope.Scope;

/**
 *
 * @author genesis
 * ForGETfull API
 */
public interface LexiaIs {

    public Goal[] GET(Scope scope);
    public Goal[] HEAD(Scope scope);
    public Goal[] PUT(Scope scope);
    public Goal[] POST(Scope scope);
    public Goal[] DELETE(Scope scope);
    public Goal[] OPTIONS(Scope scope);
 
}
