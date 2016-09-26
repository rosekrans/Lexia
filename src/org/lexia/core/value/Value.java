/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.value;

import java.io.Serializable;
import org.lexia.core.SayWhat;
import org.lexia.core.attribute.Attribute;
import org.lexia.core.format.AttributeFormat;
import org.lexia.core.format.Label;
import org.lexia.core.format.MemberFormat;
import org.lexia.core.scope.ScopeName;

/**
 *
 * @author genesis
 */
public class Value {

    public static final Label VALUE = new Label("Value");

    public final String value;
    //public final ScopeName scopeName;
    public final Boolean not;
    public final Attribute attribute;

    protected Value(Attribute attribute, String value, Boolean not) {
        //super(attribute.name);
        this.not = not;
        this.value = value;
        this.attribute = attribute;

    }

    public final ScopeName scopeName() {
        return ScopeName.scopeName(this.value, this.not);
    }

    public static final Boolean For(ScopeName scopeName) {
        switch (scopeName) {
            case ANY_MATCHING_THING:
                return null;
            case A_THING:
                return null;
            case ALL_THINGS:
                return Boolean.FALSE;
            case THE_THING:
                return Boolean.FALSE;
            case NO_ALLOWED_THING:
                return Boolean.TRUE;
            case NOT_THE_THING:
                return Boolean.TRUE;
            default:
                throw new SayWhat(scopeName.name);
        }

    }

    public final String toURI(MemberFormat format) {
        StringBuilder builder = new StringBuilder();
        appendToURI(builder, format);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, MemberFormat format) {
        format.appendLabel(builder, VALUE);
        builder.append(format.is).append(format.open);
        this.scopeName().appendToURI(builder, format.attributeFormat);
        //appendStateToURI(builder, format.attributeFormat, SCOPE, this.scopeName.name);
        builder.append(format.and);
        appendStateToURI(builder, format.attributeFormat, Attribute.NAME, this.attribute.name);
        builder.append(format.and);
        if (this.value != null) {
            appendStateToURI(builder, format.attributeFormat, VALUE, this.value.toString());
        } else {
            appendStateToURI(builder, format.attributeFormat, VALUE, null);
        }
        //if (format.open.length() > 0) {
        builder.append(format.close);
        //}
    }

    public final void appendStateToURI(StringBuilder builder, AttributeFormat format, Label label, String value) {
        format.appendLabel(builder, label);
        builder.append(format.is);
        format.appendValue(builder, value);
    }

    private static Value instance(Attribute attribute, String value, Boolean not) {
        return new Value(attribute, value, not);
    }

    public static final Value ToValue(MemberFormat format, String uri) {
        StringBuilder builder = new StringBuilder(uri);
        Value value = ToValue(format, builder);
        System.out.println("URI:"+builder.toString());
        return value;
    }

    public static final Value ToValue(MemberFormat format, StringBuilder builder) {
        Label type = format.stripLabelUpToIsOpenIncluded(builder);
        if (VALUE.equals(type)) {
            String scopeNameValue;
            String nameValue;
            String valueValue;
            Label scope = format.attributeFormat.stripLabelUpToIsIncluded(builder);
            if (ScopeName.SCOPE_NAME.equals(scope)) {
                scopeNameValue = format.stripUpToCloseOnlyIncluded(builder);
            } else {
                throw new SayWhat(scope.value, builder);
            }
            //System.out.println(ScopeName.SCOPE_NAME+":URI:"+builder);
            //
            format.stripAndIncluding(builder);
            //
            Label name = format.attributeFormat.stripLabelUpToIsIncluded(builder);
            if (Attribute.NAME.equals(name)) {
                nameValue = format.stripUpToCloseOnlyIncluded(builder);
            } else {
                throw new SayWhat(name.value, builder);
            }
            //System.out.println(Attribute.NAME.+":"+nameValue+":URI:"+builder);
            //
            format.stripAndIncluding(builder);
            //
            Label value = format.attributeFormat.stripLabelUpToIsIncluded(builder);
            if (VALUE.equals(value)) {
                valueValue = format.stripUpToCloseOnlyIncluded(builder);
            } else {
                throw new SayWhat(value.value, builder);
            }
            System.out.println(VALUE.value+":URI:"+builder);

            format.stripCloseIncluding(builder);
            //format.stripCloseIncluding(builder);
            //
            ScopeName scopeName = ScopeName.For(scopeNameValue);
            return Value.instance(Attribute.instance(nameValue), valueValue, For(scopeName));
        } else {
            throw new SayWhat(type.value, builder);
        }
    }

}
