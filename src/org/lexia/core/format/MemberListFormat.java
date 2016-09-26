/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.format;

/**
 *
 * @author genesis
 */
public class MemberListFormat extends ListFormat {
    public final MemberFormat memberFormat;

    public MemberListFormat(String open, String is, String and, String close,MemberFormat memberFormat) {
        super(open, is,memberFormat.quote, and, close);
        this.memberFormat = memberFormat;
    }
    
}
