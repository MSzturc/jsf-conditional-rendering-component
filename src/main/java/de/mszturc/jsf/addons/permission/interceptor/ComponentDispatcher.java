package de.mszturc.jsf.addons.permission.interceptor;

import de.mszturc.jsf.addons.permission.component.Requires;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.faces.component.UIComponent;
import javax.faces.component.visit.VisitResult;
import javax.inject.Inject;

/**
 * Author: MSzturc
 * Date:   30.07.2014 
 */
@ApplicationScoped
public class ComponentDispatcher {
    
    @Inject Event<RequiresRenderedEvent> event;

    VisitResult handle(UIComponent target){
        
        //TODO: Make Components registable in dispatcher
        if(target instanceof Requires){
            event.fire(new RequiresRenderedEvent((Requires) target));
        }
        return VisitResult.ACCEPT;
    }
}
