package org.myorg.blog.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * Abstract class for component sling model
 */
public abstract class AbstractComponentModel {

  @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
  protected String id;

  /**
   * @return a prefix String which will be used for generating {@link #id} through {@link #getId()}
   */
  protected abstract String getIdPrefix();

  @SlingObject
  private Resource resource;

  /**
   * Gets id from component property or generate one from component resource path.
   * @return id
   */
  public String getId() {
    if (id == null) {
      id = getIdPrefix() + "-" + String.valueOf(Math.abs(resource.getPath().hashCode() - 1));
    }
    return id;
  }
}
