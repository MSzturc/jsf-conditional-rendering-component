package de.mszturc.jsf.addons.permission.extension;

import de.mszturc.jsf.addons.permission.security.DefaultSecurityManager;
import de.mszturc.jsf.addons.permission.security.SecurityManager;
import de.mszturc.junit.Assert.Proxy;
import static de.mszturc.junit.Assert.Proxy.assertProxyInsanceOf;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Author: MSzturc
 * Date:   31.07.2014 
 */
@RunWith(Arquillian.class)
public class TestSecurityManagerFallback {

    @Inject
    SecurityManager securityManager;
    
    @Inject Instance<SecurityManager> instance;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(FallbackSecurityManagerExtension.class).addClass(AnnotatedTypeWrapper.class)
                .addClass(Proxy.class)
                .addAsManifestResource("META-INF/services/javax.enterprise.inject.spi.Extension")
                .addClass(SecurityManager.class).addClass(DefaultSecurityManager.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void useDefaultSecurityManagerAsFallback() throws Exception {
        
        assertNotNull(instance);
        assertFalse(instance.isUnsatisfied());
        assertFalse(instance.isAmbiguous());
        assertNotNull(instance.get());
        
        assertNotNull(securityManager);
        assertProxyInsanceOf(securityManager, DefaultSecurityManager.class);
    }
}
