package de.mszturc.jsf.addons.permission.extension.data;

import de.mszturc.jsf.addons.permission.security.PermissionBinding;

/**
 * Author: MSzturc
 * Date:   31.07.2014 
 */
public class UnflaggedPermissionBinding implements PermissionBinding{

    @Override
    public boolean checkPermission(String... permission) {
        return true;
    }
}
