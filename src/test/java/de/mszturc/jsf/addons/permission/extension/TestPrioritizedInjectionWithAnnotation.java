package de.mszturc.jsf.addons.permission.extension;

import de.mszturc.jsf.addons.permission.extension.FallbackSecurityManagerExtension;
import de.mszturc.jsf.addons.permission.security.DefaultSecurityManager;
import de.mszturc.jsf.addons.permission.security.SecurityManager;
import de.mszturc.jsf.addons.permission.extension.data.PrioritizedSecurityManager;
import de.mszturc.junit.Assert.Proxy;
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
import static de.mszturc.junit.Assert.Proxy.assertProxyInsanceOf;

/**
 * Author: MSzturc
 * Date:   31.07.2014 
 */
@RunWith(Arquillian.class)
public class TestPrioritizedInjectionWithAnnotation {

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
                .addClass(PrioritizedSecurityManager.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void chooseSecurityManagerByPriority() throws Exception {

        assertNotNull(instance);
        assertFalse(instance.isUnsatisfied());

        //Unfortunately isAmbiguous returns true in weld-2.1.2 ( wildfly 8.1 ),
        //which is not correct since injection of SecurityManager is working
        //assertFalse(instance.isAmbiguous());
        
        assertNotNull(instance.get());

        assertNotNull(securityManager);
        assertProxyInsanceOf(securityManager, PrioritizedSecurityManager.class);
    }
}
