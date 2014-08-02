package de.mszturc.jsf.addons.permission.component.pagemodel;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Author: MSzturc
 * Date:   02.08.2014 
 */
@Location("test.jsf")
public class TestPage {
    
    @FindBy
    WebElement title;
    
    @FindBy
    WebElement menu;

    public String getTitle() {
        return title.getText().trim();
    }

    public String getMenu() {
        return menu.getText().trim();
    }
}
