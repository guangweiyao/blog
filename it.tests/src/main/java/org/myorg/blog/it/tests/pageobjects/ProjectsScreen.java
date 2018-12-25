package org.myorg.blog.it.tests.pageobjects;

import com.cognifide.qa.bb.constants.Timeouts;
import com.cognifide.qa.bb.provider.selenium.BobcatWait;
import com.google.inject.name.Named;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;

/**
 * This component represents AEM projects page. You can leave this class as it is in your test project.
 */
@PageObject
public class ProjectsScreen {

	@Inject
	@Named("author.url")
	private String authorUrl;

	@Inject
	private WebDriver webDriver;

	@Inject
	private BobcatWait wait;

	@FindBy(css = "a.user.icon-user, a.endor-BlackBar-item.endor-UserProfile")
	private WebElement userIcon;

	@FindBy(css = "#user_dialog, #granite-user-properties")
	private UserDialog userDialog;


	public boolean startScreenIsDisplayed() {
		return "AEM Start".equals(webDriver.getTitle());
	}

	public UserDialog openUserDialog() {
		userIcon.click();
		return userDialog;
	}

	public ProjectsScreen open() {
		webDriver.get(authorUrl + "/projects.html");
		wait.withTimeout(Timeouts.MEDIUM).until(ExpectedConditions.titleIs("AEM Projects"));
		return this;
	}
}
