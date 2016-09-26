/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core;

import org.lexia.core.kind.Kind;
import org.lexia.core.role.KindRole;
import org.lexia.core.scope.Scope;


/**
 *
 * @author genesis
 */
public class Oops extends OhNo {

    public Oops(Exception e) {
        super("Oops!;" + e.getMessage(), e);
    }

    public Oops(SayWhat e) {
        super("Oops! ThIS SHOULD NEVER HAPPEN IF THERE IS A \"\" ROLENAME;" + e.getMessage(), e);
    }

    public Oops(String value) {
        super("Oops!",value);
    }

    public Oops(Long id) {
        super("Oops!",id);
    }

    public Oops(Condition condition,Kind kind) {
        super("Oops!",condition,kind);
    }
    public Oops(Condition condition,KindRole role) {
        super("Oops!",condition,role);
    }

    public Oops(Condition condition,Scope scope) {
        super("Oops!",condition,scope);
    }

    public Oops(Condition condition,String value) {
        super("Oops!",condition,value);
    }

    public Oops(Condition condition) {
        super("Oops!",condition);
    }
}
