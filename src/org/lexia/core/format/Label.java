/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.format;

import java.io.Serializable;
import java.util.Objects;
import org.lexia.core.Oops;

/**
 *
 * @author genesis
 */
public class Label implements Serializable,Comparable<Label> {

    public final String value;

    public Label(String label) {
        this.value = label;
        if (this.value == null) {
            throw new Oops(this.value);
        } else {
            if (this.value.equals("null")) {
                throw new Oops(this.value);
            }
        }
        //this.end = new StringBuilder(label).reverse().toString();

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.value);
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
        final Label other = (Label) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Label t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        throw new Oops(super.toString());
    }

    
}
