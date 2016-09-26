/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.format;

import org.lexia.core.Oops;

/**
 *
 * @author genesis /name/value --> "/","/","" name=value --> "","=",""
 */
public abstract class MemberFormat extends Format {

    public final AttributeFormat attributeFormat;

    protected MemberFormat(String open, String is, String and, String close, AttributeFormat attributeFormat) {
        super(open,is,attributeFormat.quote,and, close);
        this.attributeFormat = attributeFormat;
    }


}
