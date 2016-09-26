/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.kind;

import org.lexia.core.SayWhat;
import org.lexia.core.format.AttributeFormat;
import org.lexia.core.format.Label;

/**
 *
 * @author genesis
 */
public enum DomainSet {

    SYSTEM_COMPLIMENT_SET("n", true),
    SYSTEM_SET("s", false),
    UNIVERSAL_SET("u", null);

    public static final Label DOMAIN_SET = new Label("domain");

    public final String name;
    public final Boolean byUser;

    private DomainSet(String name, Boolean byUser) {
        this.name = name;
        this.byUser = byUser;
    }

    public final void appendToURI(StringBuilder builder, AttributeFormat format) {
        format.appendLabel(builder, DOMAIN_SET);
        builder.append(format.is);
        format.appendValue(builder, this.name);
    }

    public static final DomainSet For(String name) {
        switch (name) {
            case "n":
                return SYSTEM_COMPLIMENT_SET;
            case "s":
                return SYSTEM_SET;
            case "u":
                return UNIVERSAL_SET;
            default:
                throw new SayWhat(name);
        }
    }

    public static final DomainSet For(Boolean byUser) {
        if (byUser != null) {
            if (byUser.booleanValue()) {
                return SYSTEM_COMPLIMENT_SET;
            } else {
               return SYSTEM_SET;
            }
        } else {
            return UNIVERSAL_SET;
        }
    }
}
