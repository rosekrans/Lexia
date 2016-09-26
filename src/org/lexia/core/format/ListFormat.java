/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.format;

/**
 *
 * @author genesis /name/value --> "/","/","" name=value --> "","=",""
 */
public abstract class ListFormat extends Format {

    protected ListFormat(String open, String is,Quote quote, String and, String close) {
        super(open, is,quote, and, close);
    }

    public String stripUpToListAndIncluded(StringBuilder uri) {
        return this.stripUpToAndIncluded(uri);
    }

    public String stripUpToListOpenIncluded(StringBuilder uri) {
        Label stripped = this.stripLabelUpToIsIncluded(uri);
        return stripped.value + this.stripUpToOpenIncluded(uri);
    }

    public String stripUpToListCloseIncluded(StringBuilder uri) {
        return this.stripUpToCloseOnlyIncluded(uri);
    }

}
