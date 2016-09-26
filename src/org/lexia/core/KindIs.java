/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core;

import java.io.Serializable;
import org.lexia.core.format.MemberListFormat;

/**
 *
 * @author genesis
 */
public interface KindIs extends Serializable, Comparable<Object> {

    public String toURI(MemberListFormat format);

    public void appendToURI(StringBuilder builder, MemberListFormat listFormat);

}
