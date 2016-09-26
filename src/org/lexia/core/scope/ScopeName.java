/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.scope;

import java.io.Serializable;
import org.lexia.core.OhNo;
import org.lexia.core.SayWhat;
import org.lexia.core.format.AttributeFormat;
import org.lexia.core.format.Label;

/**
 *
 * @author genesis
 */
public enum ScopeName {

    SET_THING("set","set"),
    TOBE_THING("tobe","tobe"),
    OLD_THING("old","old"),
    A_THING("a","a"),
    ANY_MATCHING_THING("any","any"),
    MANY_MASKED_THING("many","many"),
    ALL_THINGS("all","all"),
    THE_THING("the","the"),
    FOR_THING("for","for"),
    NO_ALLOWED_THING("no","no"), //as in block, as in not a
    NOT_THE_THING("not","not"); //as in exclude,as in crossed out

    public static final Label SCOPE_NAME = new Label("scope_name");
    public String name;
    public String abbreviation;

    private ScopeName(String name,String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public static final ScopeName For(String name) {
        switch (name) {
            case "set":
                return SET_THING;
            case "a":
                return A_THING;
            case "many":
                return MANY_MASKED_THING;
            case "any":
                return ANY_MATCHING_THING;
            case "all":
                return ALL_THINGS;
            case "the":
                return THE_THING;
            case "for":
                return FOR_THING;
            case "no":
                return NO_ALLOWED_THING;
            case "not":
                return NOT_THE_THING;
            case "tobe":
                return TOBE_THING;
            case "old":
                return OLD_THING;
            default:
                throw new SayWhat(OhNo.NOT_SUPPORTED, name);
        }
    }

    public static final ScopeName scopeName(Serializable thing, Boolean not) {
        if (thing != null) {
            if (not != null) {
                if (not.booleanValue()) {
                    return NOT_THE_THING;
                } else {
                    return THE_THING;
                }
            } else {
                return ANY_MATCHING_THING;
            }

        } else {
            if (not != null) {
                if (not.booleanValue()) {
                    return NO_ALLOWED_THING;
                } else {
                    return ALL_THINGS;
                }
            } else {
                return A_THING;
            }
        }
    }

    public final void appendToURI(StringBuilder builder, AttributeFormat format) {
        format.appendLabel(builder, SCOPE_NAME);
        builder.append(format.is);
        format.appendValue(builder, this.name);
    }
}
