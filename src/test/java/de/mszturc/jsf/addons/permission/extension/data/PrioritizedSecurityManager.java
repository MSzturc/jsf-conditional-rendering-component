package de.mszturc.jsf.addons.permission.extension.data;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;

/**
 * Author: MSzturc
 * Date:   31.07.2014 
 */
@Alternative
@Priority(100)
public class PrioritizedSecurityManager implements de.mszturc.jsf.addons.permission.security.SecurityManager{

    @Override
    public boolean checkPermission(String... permission) {
        return true;
    }
}
