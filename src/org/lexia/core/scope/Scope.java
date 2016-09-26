/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.scope;

import org.lexia.core.format.Label;
import org.lexia.core.format.MemberListFormat;
import org.lexia.core.role.ActionRole;

/**
 *
 * @author genesis
 */
public class Scope {
    public static final Label SCOPE = new Label("Scope");
    public final ScopeName scopeName;
    public final ActionRole methodRole;

    protected Scope(ScopeName scopeName, ActionRole methodRole) {
        this.scopeName = scopeName;
        this.methodRole = methodRole;
    }

    public final String toURI(MemberListFormat listFormat) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, listFormat);
        return builder.toString();
    }
    public final void appendToURI(StringBuilder builder, MemberListFormat listFormat) {
        listFormat.memberFormat.appendLabel(builder, SCOPE);
        builder.append(listFormat.memberFormat.is);
        this.appendStateToURI(builder, listFormat);
    }
    
    public final void appendStateToURI(StringBuilder builder, MemberListFormat listFormat) {
        builder.append(listFormat.memberFormat.open);
        this.scopeName.appendToURI(builder, listFormat.memberFormat.attributeFormat);
        builder.append(listFormat.memberFormat.and);
        this.methodRole.appendToURI(builder, listFormat);
        builder.append(listFormat.memberFormat.close);
    }

    public static Scope instance(ScopeName scopeName, ActionRole methodRole) {
        return new Scope(scopeName, methodRole);
    }

    
    public static final Scope A(ActionRole methodRole) {
        return instance(ScopeName.A_THING, methodRole);
    }

    public static final Scope ALL(ActionRole methodRole) {
        return instance(ScopeName.ALL_THINGS, methodRole);
    }

    public static final Scope ANY(ActionRole methodRole) {
        return instance(ScopeName.ANY_MATCHING_THING, methodRole);
    }

    public static final Scope THE(ActionRole methodRole) {
        return instance(ScopeName.THE_THING, methodRole);
    }

    public static final Scope NO(ActionRole methodRole) {
        return instance(ScopeName.NO_ALLOWED_THING, methodRole);
    }

    public static final Scope NOT(ActionRole methodRole) {
        return instance(ScopeName.NOT_THE_THING, methodRole);
    }

}
