package de.mszturc.jsf.addons.permission.component.data;

/**
 * Author: MSzturc
 * Date:   31.07.2014 
 */
public class DeclineAlwaysSecurityManager implements de.mszturc.jsf.addons.permission.security.SecurityManager{

    @Override
    public boolean checkPermission(String... permission) {
        return false;
    }
}
