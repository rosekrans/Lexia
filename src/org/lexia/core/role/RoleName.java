/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.role;

import org.lexia.core.SayWhat;
import org.lexia.core.format.AttributeFormat;
import org.lexia.core.format.Label;

/**
 *
 * @author genesis
 */
public enum RoleName {

    FROM("from"),
    TO("to"),
    PREVIOUS("previous"),
    WITH("with");

    public static final Label ROLE = new Label("role");
    public final String name;

    private RoleName(String name) {
        this.name = name;
    }

    public static final RoleName For(String name) {
        switch (name) {
            case "from":
                return FROM;
            case "to":
                return TO;
            case "previous":
                return PREVIOUS;
            case "with":
                return WITH;
            default:
                throw new SayWhat(name);
        }
    }

    public final void appendToURI(StringBuilder builder, AttributeFormat format) {
        format.appendLabel(builder, ROLE);
        builder.append(format.is);
        format.appendValue(builder,this.name);
    }
}
