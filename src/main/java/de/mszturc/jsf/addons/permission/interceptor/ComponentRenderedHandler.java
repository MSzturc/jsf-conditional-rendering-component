package de.mszturc.jsf.addons.permission.interceptor;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * Author: MSzturc
 * Date:   30.07.2014 
 */
@ApplicationScoped
public class ComponentRenderedHandler extends ViewHandlerWrapper {
    
    @Inject ComponentDispatcher dispatcher;
    
    private final ViewHandler viewHandler;

    public ComponentRenderedHandler(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    @Override
    public ViewHandler getWrapped() {
        return viewHandler;
    }

    @Override
    public void renderView(FacesContext context, UIViewRoot viewToRender) throws IOException, FacesException {
        
        //TODO: Introduce some Lambda magic after migration to JDK8
        viewToRender.visitTree(VisitContext.createVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent target) {
                return dispatcher.handle(target);
            }
        });
        viewHandler.renderView(context, viewToRender);
    }
}
