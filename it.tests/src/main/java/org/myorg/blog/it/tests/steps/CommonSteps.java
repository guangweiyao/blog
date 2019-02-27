package org.myorg.blog.it.tests.steps;

import com.cognifide.qa.bb.aem.AemLogin;
import com.cognifide.qa.bb.aem.touch.pageobjects.pages.AuthorPage;
import com.cognifide.qa.bb.aem.touch.pageobjects.pages.AuthorPageFactory;
import com.cognifide.qa.bb.aem.ui.wcm.SiteAdminPage;
import com.cognifide.qa.bb.provider.selenium.BobcatWait;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.inject.Inject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.myorg.blog.it.tests.pageobjects.TitlePage;
import org.myorg.blog.it.tests.utils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class CommonSteps {

  private static final String AUTHORING_TEST_SITE_PATH = "/content/blog/en/blog-home-page/";
  /*private static final Random rand = new Random();
  private static final int RANDOM_NUMBER = rand.nextInt();
  public static final String AUTO_NUMBER = Integer.toHexString(RANDOM_NUMBER);
  public static final String DESTINATION_PATH = AUTHORING_TEST_SITE_PATH + "/auto-" + AUTO_NUMBER + "/";*/

  private static AuthorPage page;
  private static String pageTitle;
  private static String pageName;
  private String actualFieldValue = null;
  private String dialogFormBase = "form.cq-dialog";
  private String dialogFieldBase = dialogFormBase + " .coral-Form-fieldwrapper:nth-of-type";
  private String tabDialogFieldBase = dialogFormBase + " .coral-TabPanel-pane.is-active  .coral-Form-fieldwrapper:nth-of-type";
  private String multiField = ".coral-Multifield-list li.js-coral-Multifield-input";
  private String multiFieldSixThree = "coral-multifield-item.coral-Multifield-item";
  private String multiFieldSection = " section";
  private String multiFieldDialogBase = multiFieldSection + " .coral-Form-fieldwrapper:nth-of-type";

  public static By iconAdd = By.cssSelector("#EditableToolbar .coral-Icon--add");
  public static By searchField = By.cssSelector("coral-search input");
  public static By dragComp = By.cssSelector(".coral-SelectList-group .coral3-SelectList-item:nth-of-type(1)");
  public static By parentDialog = By.xpath("(//ul[@class='coral-SelectList is-visible is-inline InsertComponentDialog-list'])[last()]");
  public static By btn_next = By.xpath("button.x-btn-text.x-tbar-page-next");
  public static By btn_last = By.cssSelector("button.x-btn-text.x-tbar-page-last");
  public static By btn_previous = By.cssSelector("button.x-tbar-page-prev");
  public static By popupModes = By.cssSelector("coral-WizardView.coral-WizardView");
  public static By btnClosePopupModes = By.xpath("(//coral-button-label[text()='Close'])[2]");
  public By componentConfig = By.cssSelector("button[title='Configure']");
  public static String pageLevelOne = "(//a[@class='x-tree-node-anchor']/span[text()='";
  public static String pageLevelTwo = "']/ancestor::ul)[last()]/preceding-sibling::div//a[@class='x-tree-node-anchor']/span";
  public static String pageOne = "//td/div[text()='";
  public static String classattr = "class";

  private By dialogFieldIndexLocator = null;
  private By dialogForm = By.cssSelector("form.cq-dialog");
  private By dialogLabel = By.cssSelector("label");
  private By dialogCheckbox = By.cssSelector(".coral-Checkbox-input");
  private By dialogText = By.cssSelector("input");
  private By dialogRichtext = By.cssSelector(".coral-RichText-editable");
  private By selectDropDown = By.cssSelector(".coral-Select-select");
  private By dialogIconSelectCaret = By.cssSelector(" .selector-button i");
  private By dialogIconSelect = By.cssSelector(" .selected-icon i");
  private By button = By.cssSelector("button");
  private By dialogRadioDesc = By.cssSelector(".coral-Radio-description");
  private static By HEADER_LOCATOR = By.xpath("//div[text()='LinkList']");
  private static final By dialogListOrientation = By.xpath("//aside[@role='complementary']/ul");
  private static By dragComponentBox = By.cssSelector("div[data-path*='/bodypar/*']");

  @Inject
  @SuppressWarnings("unused")
  private WebDriver webDriver;

  @Inject
  @SuppressWarnings("unused")
  private AemLogin aemLogin;

  @Inject
  @SuppressWarnings("unused")
  private AuthorPageFactory authorPageFactory;

  @Inject
  @SuppressWarnings("unused")
  private SiteAdminPage siteadminPage;

 /* @Inject
  @SuppressWarnings("unused")
  private SiteadminPage siteadminPage;*/

  @Inject
  @SuppressWarnings("unused")
  private BobcatWait bobcatWait;

  BaseClass objBc = new BaseClass();
  TitlePage objTp = new TitlePage();
  
  @Given("^I create a \"(.*?)\" page using the \"(.*?)\" template$")
  public void iCreateAPageUsingTheTemplate(String newPage, String templateName) {
    this.iCreatePageUsingTheTemplate(AUTHORING_TEST_SITE_PATH, newPage, templateName);
    this.iOpenAPage(AUTHORING_TEST_SITE_PATH + this.getPageName());
  }

  public void iCreatePageUsingTheTemplate(String destPath, String newPage, String templateName) {
    this.setPageName(newPage);
    this.setPageTitle(newPage);
    siteadminPage.open(destPath);
    siteadminPage.createNewPage(this.getPageTitle(), this.getPageName(), templateName);
  }

  public void iOpenAPage(String pageName) {
    page = authorPageFactory.create(pageName + ".html");
    page.open();
  }

  @When("^I open the dialog for the \"(.*?)\" component$")
  public void iOpenTheDialogForTheComponent(String componentName) {
    BobcatWait.sleep(4);
    handleModesPopup();
    waitForElement(dragComponentBox, 20);
    webDriver.findElement(dragComponentBox).click();
    waitForElement(iconAdd, 15);
    webDriver.findElement(iconAdd).click();
    BobcatWait.sleep(3);
    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    try {
      webDriver.findElement(searchField).sendKeys(componentName);
      BobcatWait.sleep(5);
      webDriver.findElement(dragComp).click();
      BobcatWait.sleep(5);
      objBc.JSClick(webDriver, By.cssSelector("div[title='" + componentName + "']"));
      BobcatWait.sleep(5);
      webDriver.findElement(componentConfig).click();

    } catch (Exception e) {
      webDriver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      WebElement Dialog = webDriver.findElement(parentDialog);
      List<WebElement> elements = Dialog.findElements(By.tagName("button"));
      BobcatWait.sleep(3);
      for (WebElement ele : elements) {
        if (ele.getText().equalsIgnoreCase(componentName)) {
          ele.click();
        }
      }
      BobcatWait.sleep(5);
      switch (componentName) {
        case "Title Text Link":
          waitForElement(By.cssSelector("div[data-path*='/bodypar/title_text_link']"), 30);
          webDriver.findElement(By.cssSelector("div[data-path*='/bodypar/title_text_link']")).click();
          webDriver.findElement(componentConfig).click();
          break;

        default:
          page.getParsys("/bodypar/*").getComponent(componentName).openDialog();
          break;
      }
    }
  }

  @Then("^I set (text field|dropdown) with name \"(.*?)\" to \"(.*?)\"$")
  public void iSetTextFieldWithNameTo(String elementType, String name, String value) {

    By ele = null;
    switch (name) {
      case "Title":
        ele = objTp.TITLE_FIELD;
        break;

      default:
        ele = null;
        break;
    }
    waitForElement(ele, 30);
    if (Objects.equals(elementType, "text field")) {
      webDriver.findElement(ele).sendKeys(value);
    } else {
      new Select(webDriver.findElement(ele)).selectByVisibleText(value);
    }
  }

  @When("^I save the dialog$")
  public void iSaveTheDialog() {
    BobcatWait.sleep(4);
    webDriver.findElement(By.cssSelector(".cq-dialog-submit")).click();
    bobcatWait.withTimeout(2);
  }

  @And("^I switch to \"(Edit|Preview)\" mode$")
  public void iSwitchModes(String mode) {
    if (Objects.equals(mode, "Edit")) {
      bobcatWait.withTimeout(3);
      webDriver.switchTo().defaultContent();
      webDriver.findElement(By.cssSelector(".editor-GlobalBar-item.js-editor-GlobalBar-layerCurrent.editor-GlobalBar-layerCurrent.js-editor-LayerSwitcherTrigger.coral-MinimalButton")).click();
    } else {
      bobcatWait.sleep(4);
      webDriver.findElement(By.cssSelector("button[data-layer='Preview']")).click();
      webDriver.switchTo().frame(0);
    }
    BobcatWait.sleep(2);
  }

  public String getPageTitle() {
    return pageTitle;
  }

  public String getPageName() {
    return pageName;
  }

  public void handleModesPopup() {
    webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    if (webDriver.findElements(popupModes).size() > 0) {
      webDriver.findElement(btnClosePopupModes).click();
    }
  }

  public void waitForElement(By element, int timeOutInSeconds) {
    WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
    wait.ignoring(NoSuchElementException.class);
    wait.ignoring(ElementNotVisibleException.class);
    wait.ignoring(StaleElementReferenceException.class);
    wait.ignoring(ElementNotFoundException.class);
    wait.pollingEvery(250, TimeUnit.MILLISECONDS);
    wait.until(ExpectedConditions.visibilityOfElementLocated(element));
  }

  private static void setPageName(String newPage) {
    pageName = newPage;
    String[] array = pageName.toLowerCase().split(" ");
    pageName = array[0] + "-" + array[1] + "-page";
  }

  private static void setPageTitle(String newPage) {
    pageTitle = newPage;
    String[] array = pageTitle.toLowerCase().split(" ");
    pageTitle = array[0].substring(0, 1).toUpperCase() + array[0].substring(1) +
      " " + array[1].substring(0, 1).toUpperCase() + array[1].substring(1) + " Page";
  }

}
