package de.mszturc.jsf.addons.permission.extension.data;

/**
 * Author: MSzturc
 * Date:   31.07.2014 
 */
public class UnflaggedSecurityManager implements de.mszturc.jsf.addons.permission.security.SecurityManager{

    @Override
    public boolean checkPermission(String... permission) {
        return true;
    }
}
