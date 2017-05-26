package org.myorg.blog.core.use;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by eddieyao on 5/23/17.
 */
public class TitleUse extends WCMUsePojo {

    private static final Logger log = LoggerFactory.getLogger(TitleUse.class);

    private static final String TITLE_PROPERTY_NAME = "jcr:title";
    private static final String TYPE_PROPERTY_NAME = "type";

    private String title;
    private String type;

    @Override
    public void activate()
    {
        log.info("--- Started : TitleUse : Activate");

        title = getProperties().get(TITLE_PROPERTY_NAME, String.class);
        type = getProperties().get(TYPE_PROPERTY_NAME, String.class);

        
        if (StringUtils.isBlank(title)) {
            title = StringUtils.defaultIfEmpty(getCurrentPage().getPageTitle(), getCurrentPage().getTitle());
        }
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
