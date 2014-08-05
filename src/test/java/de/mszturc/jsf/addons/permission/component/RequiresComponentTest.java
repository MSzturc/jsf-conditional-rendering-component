package de.mszturc.jsf.addons.permission.component;

import de.mszturc.jsf.addons.permission.component.data.DeclineAlwaysPermissionBinding;
import de.mszturc.jsf.addons.permission.component.pagemodel.TestPage;
import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * Author: MSzturc
 * Date:   01.08.2014 
 */
@RunWith(Arquillian.class)
public class RequiresComponentTest {

    private static final String WEBAPP_SRC = "src/test/webapp";

    @Deployment(testable = false)
    public static WebArchive createDeployment() {

        File lib = Maven.resolver()
                        .resolve("de.mszturc:jsf-conditional-rendering-component:1.0-SNAPSHOT")
                        .withoutTransitivity().asSingleFile();
        
        return ShrinkWrap.create(WebArchive.class, "login.war")
                .addClass(DeclineAlwaysPermissionBinding.class)
                .addAsWebResource(new File(WEBAPP_SRC, "test.xhtml"))
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(new StringAsset("<faces-config version=\"2.0\"/>"), "faces-config.xml")
                .addAsLibrary(lib);
    }

    @Drone
    WebDriver browser;
    
    @Test
    @RunAsClient
    public void noPermissionToViewMenu(@InitialPage TestPage page) throws Exception {
        
        assertEquals("Menu", page.getTitle());
        assertTrue(page.getMenu().isEmpty());
        
    }
}
