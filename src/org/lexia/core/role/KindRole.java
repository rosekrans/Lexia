/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.role;

import java.util.Objects;
import org.lexia.core.format.Label;
import org.lexia.core.format.MemberListFormat;
import org.lexia.core.kind.Kind;

/**
 *
 * @author genesis
 */
public final class KindRole implements Comparable<Object>{

    public static final Label KIND_ROLE = new Label("KindRole");

    public final RoleName roleName;
    public final Kind kind;

    private KindRole(RoleName role, Kind kind) {
        this.roleName = role;
        this.kind = kind;
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.kind);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KindRole other = (KindRole) obj;
        if (this.roleName != other.roleName) {
            return false;
        }
        if (!Objects.equals(this.kind, other.kind)) {
            return false;
        }
        return true;
    }

    protected final String toURI(MemberListFormat listFormat) {
        StringBuilder builder = new StringBuilder();
        listFormat.appendLabel(builder, KIND_ROLE);
        builder.append(listFormat.memberFormat.is);
        appendToURI(builder, listFormat);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberListFormat listFormat) {
        builder.append(listFormat.memberFormat.open);
        this.roleName.appendToURI(builder, listFormat.memberFormat.attributeFormat);
        builder.append(listFormat.memberFormat.and);
        this.kind.appendToURI(builder, listFormat);
        builder.append(listFormat.memberFormat.close);
    }

    public static KindRole instance(RoleName role, Kind kind) {
        return new KindRole(role, kind);
    }

    public static final KindRole FROM(Kind kind) {
        return KindRole.instance(RoleName.FROM, kind);
    }

    public static final KindRole TO(Kind kind) {
        return KindRole.instance(RoleName.TO, kind);
    }

    public static final KindRole PREVIOUS(Kind kind) {
        return KindRole.instance(RoleName.PREVIOUS, kind);
    }

    public static final KindRole AS(Kind kind) {
        return KindRole.instance(RoleName.WITH, kind);
    }
}
