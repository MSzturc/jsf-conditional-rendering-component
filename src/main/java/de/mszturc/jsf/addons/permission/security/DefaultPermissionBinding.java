package de.mszturc.jsf.addons.permission.security;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

/**
 * Author: MSzturc
 * Date:   30.07.2014 
 */
@Priority(value = 1)
@Alternative
@ApplicationScoped
public class DefaultPermissionBinding implements PermissionBinding {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    @PostConstruct
    void onCreate() {
        logger.log(Level.WARNING, "No concret instance of PermissionBinding found in classpath, using fallback DefaultPermissionBinding.");
    }

    @Override
    public boolean checkPermission(String... permission) {
        return true;
    }
}
