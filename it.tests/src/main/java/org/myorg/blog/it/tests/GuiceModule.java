package org.myorg.blog.it.tests;

import com.cognifide.qa.bb.aem.AemCommonModule;
import com.cognifide.qa.bb.aem.touch.modules.AemTouchUiModule;
import com.cognifide.qa.bb.cumber.guice.BobcumberModule;
import com.cognifide.qa.bb.modules.CoreModule;
import com.google.inject.AbstractModule;

/**
 * This is an example implementation of Guice's module that you can use in your BDD project.
 * <p/>
 * Extend it by adding more modules or custom bindings.
 * <p/>
 * This module installs two basic Bobcat modules:
 * <ul>
 * <li>CoreModule -- all core functionality, like scope PageObjects or FrameSwitcher.
 * <li>AemClassicModule -- all common AEM elements.
 * </ul> 
 */
public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new CoreModule());
		install(new BobcumberModule());
		install(new AemCommonModule());
		install(new AemTouchUiModule());
		/*install(new Aem64FullModule());*/
	}

}
