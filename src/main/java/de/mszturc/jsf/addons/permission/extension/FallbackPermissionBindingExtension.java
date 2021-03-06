package de.mszturc.jsf.addons.permission.extension;

import de.mszturc.jsf.addons.permission.security.PermissionBinding;
import java.lang.annotation.Annotation;
import javax.annotation.Priority;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

/**
 * Author: MSzturc
 * Date:   31.07.2014 
 */
public class FallbackPermissionBindingExtension implements Extension {

    static final Annotation Alternative = new Annotation() {
        @Override
        public Class<? extends Annotation> annotationType() {
            return Alternative.class;
        }
    };

    static final Annotation PriorityMax = new Priority() {

        @Override
        public int value() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Class<? extends Annotation> annotationType() {
            return Priority.class;
        }
    };

    void processAllImplementations(@Observes ProcessAnnotatedType<? extends PermissionBinding> pat) {

        AnnotatedType<? extends PermissionBinding> annotatedType = pat.getAnnotatedType();
        Class<? extends PermissionBinding> javaClass = annotatedType.getJavaClass();
        
        if (!javaClass.isAnnotationPresent(Alternative.class) && !javaClass.isAnnotationPresent(Priority.class)) {
            AnnotatedTypeWrapper wrapper = new AnnotatedTypeWrapper<>(annotatedType, Alternative, PriorityMax);
            pat.setAnnotatedType(wrapper);
        }
    }

}
