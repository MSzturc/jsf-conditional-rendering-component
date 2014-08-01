package de.mszturc.jsf.addons.permission.security;

/**
 * Author: MSzturc
 * Date:   30.07.2014 
 */
public interface SecurityManager {

    public boolean checkPermission(String... permission);
}
