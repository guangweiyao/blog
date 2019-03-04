package org.myorg.blog.it.tests.pageobjects.impl;

import com.cognifide.qa.bb.aem.core.pages.AemAuthorPage;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.cognifide.qa.bb.wait.BobcatWait;
import com.google.inject.Inject;
import org.openqa.selenium.support.ui.ExpectedConditions;

@PageObject
public class TestPageImpl extends AemAuthorPage<TestPageImpl> {

  @Inject
  private BobcatWait bobcatWait;

  private String template;

  private String title = "English";

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setTemplate(String template) { this.template = template; }

  public boolean isDisplayed() {
    return bobcatWait.isConditionMet(ExpectedConditions.titleIs(getTitle()));
  }

  public boolean isNotAvailable() {
    return bobcatWait.isConditionMet(ExpectedConditions.titleContains("404 Resource"));
  }
}
