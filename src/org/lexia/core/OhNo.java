/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core;

import org.lexia.core.format.MemberListFormat;
import org.lexia.core.goal.Goal;
import org.lexia.core.kind.Kind;
import org.lexia.core.role.KindRole;
import org.lexia.core.scope.Scope;


/**
 *
 * @author genesis
 */
public abstract class OhNo extends RuntimeException {

    public static class Condition {

        public final String condition;

        private Condition(String condition) {
            this.condition = condition;
        }
    }

    public static final Condition NOT_UNIQUE = new Condition("NotUnique");
    public static final Condition NOT_COMPLETE = new Condition("NotComplete");
    public static final Condition MULTIPLE_FOUND = new Condition("Multiple Found");
    public static final Condition ONE_FOUND = new Condition("One Found");
    public static final Condition NONE_FOUND = new Condition("None Found");
    public static final Condition DUPLICATES_NOT_ALLOWED = new Condition("Duplicates not allowed");
    public static final Condition NULL_NOT_ALLOWED = new Condition("Null not allowed");
    public static final Condition NOT_SUPPORTED = new Condition("Not supported");

    public OhNo(String type,Exception e) {
        super(type+"; message=" + e.getMessage(), e);
    }

    public OhNo(String type,Long value) {
        super(type+"; value=" + value);
    }

    public OhNo(String type,String value) {
        super(type+"; value=" + value);
    }

    public OhNo(String type,String value,StringBuilder uri) {
        super(type+" uri="+uri+", value=" + value);
    }

    public OhNo(String type,Condition condition,Kind kind) {
        super(type+" condition=" +condition.condition+ ", kind="+ kind.kindName.name);
    }

    public OhNo(String type,Condition condition,Scope scope) {
        super(type+" condition=" +condition.condition+ ", scope="+ scope.scopeName.name);
    }

    public OhNo(String type,Condition condition,KindRole role) {
        super(type+": " +condition.condition+ ", role="+ role);
    }

    public OhNo(String type,Condition condition,String value) {
        super(type+": " +condition.condition+ ", value="+ value);
    }

    public OhNo(String type,Condition condition,Goal goal,MemberListFormat memberListFormat) {
        super(type+": " +condition.condition+ "; "+ goal.toURI(memberListFormat));
    }

    public OhNo(String type,Condition condition) {
        super(type+"; " + condition.condition);
    }
}
