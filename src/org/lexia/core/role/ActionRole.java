/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.role;

import org.lexia.core.format.MemberListFormat;
import org.lexia.core.method.Action;

/**
 *
 * @author genesis
 */
public final class ActionRole {
    public static final String METHOD_ROLE = "MethodRole";
    public final RoleName roleName;
    public final Action action;
    private ActionRole(RoleName roleName, Action method) {
        this.roleName = roleName;
        this.action = method;
    }
    
    protected final String toURI(MemberListFormat listFormat) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, listFormat);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberListFormat listFormat) {
        builder.append(METHOD_ROLE).append(listFormat.memberFormat.is);
        appendStateToURI(builder, listFormat);
    }
    
    public final void appendStateToURI(StringBuilder builder, MemberListFormat listFormat) {
        builder.append(listFormat.memberFormat.open);
        this.roleName.appendToURI(builder, listFormat.memberFormat.attributeFormat);
        builder.append(listFormat.memberFormat.and);
        this.action.appendToURI(builder, listFormat);
        builder.append(listFormat.memberFormat.close);
    }

    public static ActionRole instance(RoleName roleName, Action method) {
        return new ActionRole(roleName, method);
    }
    
    public static final ActionRole FROM(Action method) {
        return ActionRole.instance(RoleName.FROM, method);
    }
    
    public static final ActionRole TO(Action method) {
        return ActionRole.instance(RoleName.TO, method);
    }
    
    public static final ActionRole PREVIOUS(Action method) {
        return ActionRole.instance(RoleName.PREVIOUS, method);
    }

    public static final ActionRole AS(Action method) {
        return ActionRole.instance(RoleName.WITH, method);
    }
}
