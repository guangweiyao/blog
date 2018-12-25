package org.myorg.blog.it.tests.steps;

import com.cognifide.qa.bb.aem.AemLogin;
import org.junit.Assert;

import org.myorg.blog.it.tests.pageobjects.login.LoginPage;
import org.myorg.blog.it.tests.pageobjects.ProjectsScreen;
import com.google.inject.Inject;
import com.cognifide.qa.bb.provider.selenium.BobcatWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;

import java.util.Properties;

/**
 * This is an implementation of steps in the "Login" scenario.
 * <p/>
 * You can leave it as it is, as long as you don't modify the login scenario.
 */

@ScenarioScoped
public class AuthorizationSteps {

	/**
	 * Scenario is about logging in AEM, so you need an instance of the LoginPage.
	 */
	@Inject
	private LoginPage loginPage;

	/**
	 *
	 */
	@Inject
	private AemLogin aemLogin;

	/**
	 * Instance of ProjectsScreen will help to check if the login procedure was successful.
	 */
	@Inject
	private ProjectsScreen projectsScreen;

	@Inject
	private Properties properties;

	@Given("^I have opened login page$")
	public void I_have_opened_login_page() {
		Assert.assertTrue("Login page is not displayed", loginPage.openLoginPage().loginPageIsDisplayed());
	}

	@Given("^I am not logged in")
	public void I_am_not_logged_in() {
		Assert.assertFalse("Login did not succeed", projectsScreen.startScreenIsDisplayed());
	}

	@When("^I enter following credentials \"(.+)\", \"(.+)\"$")
	public void I_enter_following_credentials(String login, String password) {
		loginPage.getLoginBox().enterLogin(login);
		BobcatWait.sleep(1);
		loginPage.getLoginBox().enterPassword(password);
	}

	@When("^I enter following credentials defined by properties \"(.+)\", \"(.+)\"$")
	public void I_enter_following_credentials_defined_by_properties(String loginProperty, String passwordProperty) {
		String login = properties.getProperty(loginProperty);
		String password = properties.getProperty(passwordProperty);
		I_enter_following_credentials(login, password);
	}

	@When("^I press login button$")
	public void I_press_login_button() {
		loginPage.getLoginBox().clickSignIn();
	}

	@Then("^I should see welcome page$")
	public void I_should_see_welcome_page() {
		Assert.assertTrue("Welcome page is not displayed", projectsScreen.startScreenIsDisplayed());
	}

	@Then("^Authorization error message should appear$")
	public void Error_message_should_appear() {
		Assert.assertTrue("Error message is not visible", loginPage.getLoginBox().isErrorMessageVisible());
	}

	@Given("^I am logged in$")
	public void I_am_logged_in() {
		aemLogin.authorLogin();
	}

	@And("^I have opened projects page$")
	public void I_have_opened_projects_page() {
		projectsScreen.open();
		Assert.assertTrue("Projects page is not displayed", projectsScreen.startScreenIsDisplayed());
	}

	@When("^I press logout button$")
	public void I_press_logout_button() {
		projectsScreen.openUserDialog().signOut();
	}
}
