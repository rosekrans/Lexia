/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.format;

import java.util.Set;
import org.lexia.core.attribute.Attribute;

/**
 *
 * @author genesis /name/value --> "/","/","" name=value --> "","=",""
 */
public class ValueListFormat extends ListFormat {
    public static final Label LIST = new Label("List");
    //public final MemberFormat memberFormat;
    public final AttributeFormat attributeFormat;

    public ValueListFormat(String open, String is,Quote quote, String and, String close,AttributeFormat attributeFormat) {
        super(open, is,quote, and, close);
        //this.memberFormat = memberFormat;
        this.attributeFormat = attributeFormat;
    }

    public final String toURI(Set<Attribute> attributes) {
        StringBuilder builder = new StringBuilder();
        this.appendLabel(builder, LIST);
        builder.append(is).append(open);
        appendToURI(builder, attributes);
        builder.append(close);
        return builder.toString();
    }

    public final void appendToURI(StringBuilder builder, Set<Attribute> attributes) {
        String and = "";
        for (Attribute attribute : attributes) {
            builder.append(and);
            attribute.appendStateToURI(builder, attributeFormat,Attribute.ATTRIBUTE);
            and = this.and;
        }
    }

}
