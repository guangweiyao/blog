package org.myorg.blog.core.models;

import com.adobe.cq.sightly.WCMBindings;
import com.day.cq.wcm.api.Page;
import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.models.impl.ResourceTypeBasedResourcePicker;
import org.apache.sling.models.spi.ImplementationPicker;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * Created by eddieyao on 6/3/17.
 */
public class TitleModelTest {

    private static final String TEST_TITLE_RESOURCE_CONTENT = "/title/test-content.json";
    private static final String TEST_CONTENT_ROOT = "/content/title";
    private static final String TITLE_RESOURCE = TEST_CONTENT_ROOT + "/jcr:content/bodypar/title";

    @Rule
    public AemContext context = new AemContext();

    private TitleModel underTest;
    private SlingBindings slingBindings;
    private Resource resource;

    @Before
    public void setup() {

        context.registerService(ImplementationPicker.class, new ResourceTypeBasedResourcePicker());
        context.addModelsForPackage("org.myorg.blog.core.models");

        context.load().json(TEST_TITLE_RESOURCE_CONTENT, TEST_CONTENT_ROOT);

        Page page = context.currentPage(TEST_CONTENT_ROOT);
        slingBindings = (SlingBindings) context.request().getAttribute(SlingBindings.class.getName());
        slingBindings.put(WCMBindings.CURRENT_PAGE, page);

        resource = context.currentResource(TITLE_RESOURCE);

    }

    @Test
    public void testGetTitleFromResource() {
        underTest = context.request().adaptTo(TitleModel.class);
        assertEquals(underTest.getTitle(), "Blog English Homepage");
        assertEquals(underTest.getType(), "h2");
    }
}
