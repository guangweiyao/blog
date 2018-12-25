package org.myorg.blog.it.tests.hooks;

import com.google.inject.Inject;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;

/**
 * A helper class that will maximize window
 */
public class WindowMaximize {

	@Inject
	private WebDriver webDriver;

	@Before
	public void maximize() {
		webDriver.manage().window().maximize();
	}
}
