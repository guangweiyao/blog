package org.myorg.blog.it.tests.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.cognifide.qa.bb.qualifier.PageObject;

/**
 * This PageObject represents the user dialog that appears after clicking user icon. You can leave it as it is in your test project.
 */
@PageObject
public class UserDialog {

	@FindBy(partialLinkText = "Sign Out")
	private WebElement logoutButton;

	public UserDialog signOut() {
		logoutButton.click();
		return this;
	}
}
