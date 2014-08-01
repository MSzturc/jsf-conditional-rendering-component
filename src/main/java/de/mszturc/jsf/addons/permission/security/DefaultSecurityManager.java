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
public class DefaultSecurityManager implements SecurityManager {

    private final Logger logger = Logger.getLogger(this.getClass().getName());
    
    @PostConstruct
    void onCreate() {
        logger.log(Level.WARNING, "No concret instance of SecurityManager found in classpath, using fallback DefaultSecurityManager.");
    }

    @Override
    public boolean checkPermission(String... permission) {
        return true;
    }
}
