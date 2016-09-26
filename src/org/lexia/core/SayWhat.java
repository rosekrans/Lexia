/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core;

import org.lexia.core.format.MemberListFormat;
import org.lexia.core.goal.Goal;
import org.lexia.core.kind.Kind;
import org.lexia.core.scope.Scope;



/**
 *
 * @author genesis
 */
public class SayWhat extends OhNo {

    public SayWhat(Exception e) {
        super("Say What?",e);
    }

    public SayWhat(Long id) {
        super("Say What?",id);
    }
    
    public SayWhat(String value) {
        super("Say What?",value);
    }

    public SayWhat(String value,StringBuilder uri) {
        super("Say What?",value,uri);
    }

    public SayWhat(Condition condition,String value) {
        super("Say What?",condition,value);
    }

    public SayWhat(Condition condition,Kind kind) {
        super("Say What?",condition,kind);
    }

    public SayWhat(Condition condition,Scope scope) {
        super("Say What?",condition,scope);
    }

    public SayWhat(Condition condition,Goal goal,MemberListFormat memberListFormat) {
        super("Say What?",condition,goal,memberListFormat);
    }

    public SayWhat(Condition condition) {
        super("Say What?",condition);
    }
}
