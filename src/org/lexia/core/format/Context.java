/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lexia.core.format;

import org.lexia.core.kind.DomainSet;
import org.lexia.core.goal.Goal;
import org.lexia.core.kind.Kind;
import org.lexia.core.role.KindRole;
import org.lexia.core.role.ActionRole;
import org.lexia.core.scope.Scope;
import org.lexia.core.scope.ScopeName;

/**
 *
 * @author genesis
 */
public class Context {

    public final ScopeName scopeName;
    public final DomainSet domainType;

    private Context(Context parentContext, DomainSet domainType, ScopeName scopeName) {
        this.domainType = domainType;
        this.scopeName = scopeName;
    }

    public Context() {
        this(null, null, null);
    }

    public Context childContext(DomainSet domainType, ScopeName scopeName) {
        return new Context(this, domainType, scopeName);
    }

    public Context childContext(Kind kind) {
        return new Context(this, kind.domainSet, this.scopeName);
    }

    public Context childContext(Scope scope) {
        return new Context(this, this.domainType, scope.scopeName);
    }

    public Context childContext(Goal goal) {
        return new Context(this, this.domainType, this.scopeName);
    }

    public Context childContext(DomainSet domainType) {
        return new Context(this, domainType, this.scopeName);
    }

    public Context childContext(ActionRole role) {
        return new Context(this, this.domainType, this.scopeName);
    }

    public Context childContext(KindRole role) {
        return new Context(this, this.domainType, this.scopeName);
    }


}
