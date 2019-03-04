package org.myorg.blog.it.tests.steps;

import com.cognifide.qa.bb.aem.core.api.AemActions;
import com.cognifide.qa.bb.api.actions.ActionException;
import com.cognifide.qa.bb.api.actions.ActionsController;
import com.cognifide.qa.bb.page.BobcatPageFactory;
import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import org.myorg.blog.it.tests.pageobjects.impl.TestPageImpl;

import java.util.Properties;

import static org.junit.Assert.assertTrue;

/**
 * This is an implementation of steps in the "Login" scenario.
 * <p/>
 * You can leave it as it is, as long as you don't modify the login scenario.
 */

@ScenarioScoped
public class AuthorizationSteps {

	private TestPageImpl testPage;

	@Inject
	private Properties properties;

	@Inject
	private ActionsController controller;

	@Inject
	private BobcatPageFactory bobcatPageFactory;

	@Given("^I have opened test page$")
	public void I_have_opened_login_page() {
		testPage = bobcatPageFactory.create("/content/we-retail/us/en.html", TestPageImpl.class);
	}

	@When("^I login$")
	public void I_press_login_button() throws ActionException {
		controller.execute(AemActions.LOG_IN);
	}

	@Then("^I should see test page$")
	public void I_should_see_welcome_page() {
		assertTrue(testPage.open().isDisplayed());
	}

}
