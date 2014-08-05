package de.mszturc.jsf.addons.permission.component;

import de.mszturc.jsf.addons.permission.security.PermissionBinding;
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

    @Inject PermissionBinding permissionBinding;

    public void onEvent(@Observes RequiresRenderedEvent event) {
        Requires requires = event.getRequires();
        if (!permissionBinding.checkPermission(requires.getPermission().split(","))) {
            requires.getParent().setRendered(false);
        }
    }
}
