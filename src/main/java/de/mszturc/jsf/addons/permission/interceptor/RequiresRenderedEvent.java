package de.mszturc.jsf.addons.permission.interceptor;

import de.mszturc.jsf.addons.permission.component.Requires;

/**
 * Author: MSzturc
 * Date:   30.07.2014 
 */
public class RequiresRenderedEvent {

    private final Requires requires;

    public RequiresRenderedEvent(Requires requires) {
        this.requires = requires;
    }

    public Requires getRequires() {
        return requires;
    }
}
