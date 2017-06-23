package org.myorg.blog.core.models.injectors;

import com.day.cq.commons.inherit.HierarchyNodeInheritanceValueMap;
import com.day.cq.commons.inherit.InheritanceValueMap;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.spi.DisposalCallbackRegistry;
import org.apache.sling.models.spi.Injector;
import org.osgi.framework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;

/**
 * Created by eddieyao on 6/19/17.
 */
@Component
@Service
@Property(name = Constants.SERVICE_RANKING, intValue = Integer.MAX_VALUE)
public class InheritedPropertyInjector implements Injector {

    private static final Logger log = LoggerFactory.getLogger(InheritedPropertyInjector.class);

    @Override
    public @Nonnull
    String getName() {
        return "inherited-property";
    }

    @Override
    public Object getValue(@Nonnull Object adaptable, String name, @Nonnull Type declaredType, @Nonnull AnnotatedElement element,
                           @Nonnull DisposalCallbackRegistry callbackRegistry) {
        if (adaptable instanceof Resource) {
            log.debug("--------------------adaptable is Resource");
            log.debug("--------------------name is {}", name);
            return getInheritedProperty((Resource)adaptable, name, declaredType);
            
        } else if (adaptable instanceof SlingHttpServletRequest) {
            log.debug("--------------------adaptable is SlingHttpServletRequest");
            Resource adaptableResource = ((SlingHttpServletRequest) adaptable).getResource();
            return getInheritedProperty(adaptableResource, name, declaredType);
        }
        return null;
    }

    private Object getInheritedProperty (Resource resource, String propertyName, Type declaredType) {
        log.debug("InheritedPropertyInjector--------------------getInheritedProperty method started.");
        log.debug("InheritedPropertyInjector--------------------resource path is {}", resource.getPath());
        log.debug("InheritedPropertyInjector--------------------propertyName is {}", propertyName);
        log.debug("InheritedPropertyInjector--------------------declaredType is {}", declaredType.getTypeName());

        InheritanceValueMap iProperties = new HierarchyNodeInheritanceValueMap(resource);
        return iProperties.getInherited(propertyName, (Class<?>) declaredType);

    }

    

    
}
