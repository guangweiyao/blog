package org.myorg.blog.core.use;

import io.wcm.testing.mock.aem.junit.AemContext;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.spy;

/**
 * Created by eddieyao on 6/4/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({TitleUse.class, LoggerFactory.class})
public class TitleUseTest {

    private static final String TEST_TITLE_RESOURCE_CONTENT = "/title/test-content.json";
    private static final String TEST_CONTENT_ROOT = "/content/title";
    private static final String TITLE_RESOURCE = TEST_CONTENT_ROOT + "/jcr:content/bodypar/title";

    @Rule
    public AemContext context = new AemContext();

    private TitleUse underTest;
    private ValueMap properties;
    private Resource resource;
    
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        underTest = spy(new TitleUse());

        context.load().json(TEST_TITLE_RESOURCE_CONTENT, TEST_CONTENT_ROOT);
        resource = context.currentResource(TITLE_RESOURCE);
        properties = resource.adaptTo(ValueMap.class);
        
        PowerMockito.doReturn(properties).when(underTest).getProperties();
        Mockito.doCallRealMethod().when(underTest).activate();
    }

    @Test
    public void testGetTitleFromResource() {
        underTest.activate();
        assertEquals(underTest.getTitle(), "Blog English Homepage");
        assertEquals(underTest.getType(), "h2");
    }
    
}
