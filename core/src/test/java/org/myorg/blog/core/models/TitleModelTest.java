package org.myorg.blog.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.scripting.WCMBindingsConstants;
import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.scripting.SlingBindings;
import org.apache.sling.models.impl.ResourceTypeBasedResourcePicker;
import org.apache.sling.models.spi.ImplementationPicker;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * Created by eddieyao on 6/3/17.
 */
public class TitleModelTest {
    
    private static final String MOCK_JSON = "/title/test-content.json";
    private static final String TEST_CONTENT_ROOT = "/content/title";
    private static final String TITLE_RESOURCE = TEST_CONTENT_ROOT + "/jcr:content/bodypar/title";
    private static final String EMPTY_TITLE_RESOURCE = TEST_CONTENT_ROOT + "/jcr:content/bodypar/empty-title";

    @Rule
    public AemContext context = new AemContext();

    private TitleModel underTest;
    private SlingBindings slingBindings;
    private Resource resource;
    private Page page;

    @Before
    public void setup() {
        context.addModelsForClasses(TitleModel.class);
        context.load().json(MOCK_JSON, TEST_CONTENT_ROOT);
        context.registerService(ImplementationPicker.class, new ResourceTypeBasedResourcePicker());
        page = context.currentPage(TEST_CONTENT_ROOT);
        slingBindings = (SlingBindings) context.request().getAttribute(SlingBindings.class.getName());
        slingBindings.put(WCMBindingsConstants.NAME_CURRENT_PAGE, page);
    }

    @Test
    public void testTitleFromResource() {
        resource = context.currentResource(TITLE_RESOURCE);
        underTest = context.request().adaptTo(TitleModel.class);
        assertEquals("Blog English Homepage", underTest.getTitle());
    }

    @Test
    public void testTitleFromPage() {
        resource = context.currentResource(EMPTY_TITLE_RESOURCE);
        underTest = context.request().adaptTo(TitleModel.class);
        assertEquals("English", underTest.getTitle());
    }

    @Test
    public void testType() {
        resource = context.currentResource(TITLE_RESOURCE);
        underTest = context.request().adaptTo(TitleModel.class);
        assertEquals("h2", underTest.getType());
    }

    @Test
    public void testTypeNull() {
        resource = context.currentResource(EMPTY_TITLE_RESOURCE);
        underTest = context.request().adaptTo(TitleModel.class);
        assertNull(underTest.getType());
    }

    @Test
    public void testIdPrefix() {
        underTest = context.request().adaptTo(TitleModel.class);
        assertEquals("title", underTest.getIdPrefix());
    }

    @Test
    public void testIdFromResource() {
        resource = context.currentResource(TITLE_RESOURCE);
        underTest = context.request().adaptTo(TitleModel.class);
        assertEquals("titleId", underTest.getId());
    }

    @Test
    public void testIdFromPath() {
        resource = context.currentResource(EMPTY_TITLE_RESOURCE);
        underTest = context.request().adaptTo(TitleModel.class);
        assertEquals("title-2087820980", underTest.getId());
    }
}
