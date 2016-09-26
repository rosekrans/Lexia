/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.kind;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.lexia.core.KindIs;
import org.lexia.core.attribute.Attribute;
import org.lexia.core.format.Label;
import org.lexia.core.role.KindRole;
import org.lexia.core.format.MemberListFormat;
import org.lexia.core.legend.Legend;

/**
 *
 * @author genesis
 */
public class Kind implements Comparable<Object>, KindIs{

    public static final Label KIND = new Label("Kind");
    public static final Label DOMAIN_SET = new Label("Domain");
    public static final Label ASPECTS = new Label("Aspects");
    public static final Label IDENTITY = new Label("Identity");

    public final DomainSet domainSet;
    public final Set<Attribute> aspectAttributes;
    public final KindName kindName;
    public final Shape shape;
    public final Legend identityLegend;

    protected Kind(DomainSet domainSet, KindName name, Legend identityLegend, Shape shape, Set<Attribute> aspectAttributes) {
        this.domainSet = domainSet;
        this.kindName = name;
        this.shape = shape;
        //this.handleKinds = handleKinds;
        this.identityLegend = identityLegend;
        this.aspectAttributes = aspectAttributes;
    }

    public boolean unique() {
        return this.equals(this);
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.domainSet);
        hash = 79 * hash + Objects.hashCode(this.kindName);
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
        final Kind other = (Kind) obj;
        if (!Objects.equals(this.domainSet, other.domainSet)) {
            return false;
        }
        /*if (!Objects.equals(this.aspectAttributes, other.aspectAttributes)) {
         return false;
         }*/
        if (!Objects.equals(this.kindName, other.kindName)) {
            return false;
        }
        if (!Objects.equals(this.shape, other.shape)) {
            return false;
        }
        if ((this.identityLegend == null)&&(other.identityLegend == null)) {
            return true;
        }
        if (!Objects.equals(this.identityLegend, other.identityLegend)) {
            return false;
        }
        return true;
    }

    public final String toURI(MemberListFormat format) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, format);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberListFormat listFormat) {
        listFormat.appendLabel(builder, KIND);
        builder.append(listFormat.memberFormat.is).append(listFormat.memberFormat.open);
        this.kindName.appendToURI(builder, listFormat.memberFormat);
        builder.append(listFormat.memberFormat.and);
        this.domainSet.appendToURI(builder, listFormat.memberFormat.attributeFormat);
        builder.append(listFormat.memberFormat.and);
        this.shape.appendToURI(builder, listFormat);
        builder.append(listFormat.memberFormat.and);
        if (this.identityLegend != null) {
            this.identityLegend.appendToURI(builder, listFormat,IDENTITY);
        } else {
            listFormat.appendLabel(builder, IDENTITY);
            builder.append(listFormat.memberFormat.is).append("null");
        }
        builder.append(listFormat.memberFormat.close);
    }


    protected static final Kind instance(DomainSet isSubject, Set<Attribute> groupAttributes, KindName kindName, Shape binding, Legend identityLegend) {
        return new Kind(isSubject, kindName, identityLegend, binding, groupAttributes);
    }

    private static final Kind shapeExtend(Kind kind, KindRole extraKindRole) {
        Set<KindRole> kindRoles = new HashSet<>();
        kindRoles.addAll(kind.shape.kindRoles);
        kindRoles.add(extraKindRole);
        Shape shape = new Shape(kindRoles);
        return new Kind(kind.domainSet, kind.kindName, kind.identityLegend, shape, kind.aspectAttributes);
    }

    protected static final Kind namedAndShaped(DomainSet isSubject, Set<Attribute> groupAttributes, KindName kindName, Shape shape, Legend identityLegend) {
        return new Kind(isSubject, kindName, identityLegend, shape, groupAttributes);
    }

    protected static final Kind shaped(DomainSet isSubject, Set<Attribute> groupAttributes, KindName kindName, Shape shape) {
        return new Kind(isSubject, kindName, null, shape, groupAttributes);
    }

    protected static final Kind named(DomainSet domainSet, KindName kindName, Legend identityLegend) {
        return new Kind(domainSet, kindName, identityLegend, new Shape(new HashSet<KindRole>()), new HashSet<Attribute>());
    }

}
