package org.myorg.blog.it.tests.pageobjects.impl;

import com.cognifide.qa.bb.qualifier.CurrentScope;
import com.cognifide.qa.bb.qualifier.PageObject;
import com.google.inject.Inject;
import org.myorg.blog.it.tests.pageobjects.TitleComponent;
import org.openqa.selenium.WebElement;

@PageObject(css = ".title")
public class TitleComponentImpl implements TitleComponent {
  @Inject
  @CurrentScope
  private WebElement component;

  public String getTitle() { return component.getText(); }
}
