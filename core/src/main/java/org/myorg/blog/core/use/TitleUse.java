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

    private String title;

    @Override
    public void activate()
    {
        log.info("--- Started : TitleUse : Activate");

        title = getProperties().get(TITLE_PROPERTY_NAME, String.class);
        
        if (StringUtils.isBlank(title)) {
            title = StringUtils.defaultIfEmpty(getCurrentPage().getPageTitle(), getCurrentPage().getTitle());
        }
    }

    public String getTitle() {
        return title;
    }
}
