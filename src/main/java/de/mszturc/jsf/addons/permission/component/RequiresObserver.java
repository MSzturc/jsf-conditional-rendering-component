package de.mszturc.jsf.addons.permission.component;

import de.mszturc.jsf.addons.permission.security.SecurityManager;
import de.mszturc.jsf.addons.permission.interceptor.RequiresRenderedEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 * Author: MSzturc
 * Date:   30.07.2014 
 */
@ApplicationScoped
public class RequiresObserver {

    @Inject SecurityManager securityManager;

    public void onEvent(@Observes RequiresRenderedEvent event) {
        Requires requires = event.getRequires();
        if (!securityManager.checkPermission(requires.getPermission().split(","))) {
            requires.getParent().setRendered(false);
        }
    }
}
