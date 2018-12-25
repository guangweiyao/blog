package org.myorg.blog.it.tests.pageobjects.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.constants.Timeouts;
import com.cognifide.qa.bb.provider.selenium.BobcatWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.cognifide.qa.bb.utils.PageObjectInjector;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * This class represents AEM login page.
 * <p/>
 * You can leave it as it is in your test project.
 */
@PageObject
public class LoginPage {
	/**
	 * WebDriver instance needed to open the page.
	 */
	@Inject
	private WebDriver webDriver;

	/**
	 * We'll be waiting for some WebElements to appear on the page, so we need an instance of BobcatWait.
	 */
	@Inject
	private BobcatWait bobcatWait;

	/**
	 * We'll use this injector to tell Bobcat to create an object within manually defined frame.
	 */
	@Inject
	private PageObjectInjector injector;

	/**
	 * URL of the author instance. Bobcat will inject value taken from the author.url property.
	 */
	@Inject
	@Named("author.url")
	private String authorUrl;

	/**
	 * LoginBox PageObject, looked up with FindBy annotation.
	 */
	@FindBy(id = "login-box")
	private LoginBox loginBox;

	/**
	 * @return Instance of the LoginBox associated with this page.
	 */
	public LoginBox getLoginBox() {
		return loginBox;
	}

	/**
	 * Open the login page.
	 */
	public LoginPage openLoginPage() {
		webDriver.get(authorUrl);
		bobcatWait.withTimeout(Timeouts.MEDIUM).until(ExpectedConditions.visibilityOfElementLocated(By.id("login-box")));
		return this;
	}

	/**
	 * @return True if the login page is displayed. False otherwise.
	 */
	public boolean loginPageIsDisplayed() {
		return "AEM Sign In".equals(webDriver.getTitle());
	}
}
