/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.kind;

import java.util.Objects;
import org.lexia.core.format.Label;
import org.lexia.core.format.MemberFormat;


/**
 *
 * @author genesis
 */
public class KindName {
    public static final Label KIND_NAME = new Label("kind");
    public final String name;
    public final String abbreviation;
    protected KindName(String name,String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KindName other = (KindName) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    protected static KindName instance(String name,String abbreviation) {
        return new KindName(name,abbreviation);
    }
    
    public static KindName For(String name,String abbreviation) {
        return instance(name,abbreviation);
    }
    
    public final String toURI(MemberFormat format) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, format);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberFormat format) {
        format.appendLabel(builder, KIND_NAME);
        builder.append(format.attributeFormat.is);
        format.appendValue(builder, this.name);
    }
}
