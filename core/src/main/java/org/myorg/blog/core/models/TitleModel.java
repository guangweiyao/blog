package org.myorg.blog.core.models;

import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

/**
 * Created by eddieyao on 5/23/17.
 */
@Model(adaptables=SlingHttpServletRequest.class, resourceType = "blog/components/content/title")
@Exporter(name = "jackson", extensions = "json")
public class TitleModel {

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

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
    
}
