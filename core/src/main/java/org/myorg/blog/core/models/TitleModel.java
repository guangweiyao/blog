package org.myorg.blog.core.models;

import com.adobe.cq.export.json.ExporterConstants;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.myorg.blog.core.constant.ComponentSlingModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * Sling model for title component
 */
@Model(adaptables=SlingHttpServletRequest.class,
  adapters = {TitleModel.class},
  resourceType = {ComponentSlingModel.TITLE_RESOURCE_TYPE})
@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME, extensions = ExporterConstants.SLING_MODEL_EXTENSION)
public class TitleModel extends AbstractComponentModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(TitleModel.class);

    private static final String ID_PREFIX = "title";

    @ScriptVariable
    private Page currentPage;

    @ValueMapValue(optional = true, name = JcrConstants.JCR_TITLE)
    private String title;

    @ValueMapValue(optional = true)
    private String type;

    @PostConstruct
    private void initModel() {
        if (StringUtils.isBlank(title)) {
            title = StringUtils.defaultIfEmpty(currentPage.getPageTitle(), currentPage.getTitle());
        }
    }

    @Override
    protected String getIDPrefix() {
        return ID_PREFIX;
    }

    public String getTitle() { return title; }

    public String getType() { return type; }
    
}
