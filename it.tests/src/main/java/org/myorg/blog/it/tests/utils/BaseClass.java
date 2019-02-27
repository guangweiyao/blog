package org.myorg.blog.it.tests.utils;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class BaseClass {

    @Inject
    @SuppressWarnings("unused")
    private WebDriver webDriver;

    public void waitForElementPresent(By object, int time) throws TimeoutException {
            try{
                WebDriverWait wait = new WebDriverWait(webDriver, time);
                WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(object));
            }catch(NoSuchElementException noelement){
                System.out.println("element not present on the page");
            }
    }

    public void click(By object){
        try{
            webDriver.findElement(object).click();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void sendKeys(By object, String value){
        try{
            webDriver.findElement(object).sendKeys(value);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void selectDropDownByValue(By object, String value){
    try{
        Select select = new Select(webDriver.findElement(object));
        select.selectByValue(value);
    }
    catch (Exception e){
        e.printStackTrace();
        }
    }

    public void selectDropDownByIndex(By object, int index){
        try{
            Select select = new Select(webDriver.findElement(object));
            select.selectByIndex(index);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }


    public void implicitWait(int seconds){
        webDriver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);

    }



    public boolean isElementPresent(By object) {
        try {
            webDriver.findElement((object));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
        catch (ElementNotVisibleException b){
            return false;
        }

    }

    public boolean isElementDisplayed(By object){
        try{
            if(webDriver.findElement(object).isDisplayed()){
                return true;

            }
            return false;
        }
        catch(NoSuchElementException e){
            return false;
        }
    }


    public void sleep(int n){
        try{
            Thread.sleep(n * 1000);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void JSClick(WebDriver webDriver, By object){
        try {
            WebElement element = webDriver.findElement(object);
            JavascriptExecutor executor = (JavascriptExecutor)webDriver;
            executor.executeScript("arguments[0].click();", element);
        }
        catch(Exception E){
            E.printStackTrace();
        }
    }

    public void clickAction(By object){
        try{
            WebElement element = webDriver.findElement(object);
            Actions builder = new Actions(webDriver);
            builder.moveToElement(element).click(element);
            builder.moveToElement(element).click(element);
            builder.perform();
        }
        catch (Exception E){
            E.printStackTrace();
        }
    }



}
