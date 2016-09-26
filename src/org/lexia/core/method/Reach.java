/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.method;

import java.util.HashSet;
import java.util.Set;
import org.lexia.core.format.MemberListFormat;
import org.lexia.core.scope.Scope;

/**
 *
 * @author genesis
 */
public class Reach {
    public static final String REACH = "Reach";
    public final Set<Scope> scopes;
 
    public Reach() {
        this(new HashSet<Scope>());
    }

    public Reach(Set<Scope> scopes) {
        this.scopes = scopes;
    }
    
    public final String toURI(MemberListFormat format) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, format);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberListFormat format) {
        builder.append(REACH);
        builder.append(format.is);
        if (scopes.size() > 0) {
            builder.append(format.open);
            String and = "";
            for (Scope scope : this.scopes) {
                builder.append(and);
                scope.appendStateToURI(builder, format);
                and = format.and;
            }
            builder.append(format.close);
        } else {
            if (format.open.length() > 0) {
                builder.append(format.open);
                builder.append(format.close);
            } else {
                builder.append("empty");
            }
        }
    }
}
