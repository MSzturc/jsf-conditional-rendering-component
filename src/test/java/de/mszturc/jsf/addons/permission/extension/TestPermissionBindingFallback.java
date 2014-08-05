package de.mszturc.jsf.addons.permission.extension;

import de.mszturc.jsf.addons.permission.security.DefaultPermissionBinding;
import de.mszturc.jsf.addons.permission.security.PermissionBinding;
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
public class TestPermissionBindingFallback {

    @Inject
    PermissionBinding permissionBinding;
    
    @Inject Instance<PermissionBinding> binding;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(FallbackPermissionBindingExtension.class).addClass(AnnotatedTypeWrapper.class)
                .addClass(Proxy.class)
                .addAsManifestResource("META-INF/services/javax.enterprise.inject.spi.Extension")
                .addClass(PermissionBinding.class).addClass(DefaultPermissionBinding.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void useDefaultPermissionBindingAsFallback() throws Exception {
        
        assertNotNull(binding);
        assertFalse(binding.isUnsatisfied());
        assertFalse(binding.isAmbiguous());
        assertNotNull(binding.get());
        
        assertNotNull(permissionBinding);
        assertProxyInsanceOf(permissionBinding, DefaultPermissionBinding.class);
    }
}
