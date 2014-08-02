package de.mszturc.jsf.addons.permission.component;

import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponentBase;

/**
 * Author: MSzturc
 * Date:   29.07.14
 */
@FacesComponent(value = "requires")
public class Requires extends UIComponentBase {
    
    private String permission;

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String getFamily() {
        return "de.mszturc.jsf.addons.permission";
    }
}
