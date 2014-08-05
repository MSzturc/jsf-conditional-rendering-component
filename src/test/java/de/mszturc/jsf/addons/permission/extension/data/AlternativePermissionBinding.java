package de.mszturc.jsf.addons.permission.extension.data;

import de.mszturc.jsf.addons.permission.security.PermissionBinding;
import javax.enterprise.inject.Alternative;

/**
 * Author: MSzturc
 * Date:   31.07.2014 
 */
@Alternative
public class AlternativePermissionBinding implements PermissionBinding{

    @Override
    public boolean checkPermission(String... permission) {
        return true;
    }
}
