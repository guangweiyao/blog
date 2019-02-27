package org.myorg.blog.it.tests.steps;

import com.google.inject.Inject;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class Title {

  @Inject
  @SuppressWarnings("unused")
  private WebDriver webDriver;

  @Inject
  @SuppressWarnings("unused")
  private CommonSteps commonSteps;


  @Then("^I should see the Header Text is \"(.*?)\"$")
  public void iVerifyTheHeaderText(String expectedHeaderTitle) {
    WebDriverWait wait = new WebDriverWait(webDriver, 30);
    WebElement headerTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".title")));
    assertEquals(expectedHeaderTitle, headerTitle.getText());
  }

}
