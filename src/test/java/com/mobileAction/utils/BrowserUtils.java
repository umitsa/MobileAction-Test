package com.mobileAction.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BrowserUtils {

    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    /**
     * Extracts text from list of elements matching the provided locator into new List<String>
     *
     * @param pageURL
     * @return list of WebElement
     */
    public static List<WebElement> getPageLinks(String pageURL) {
        List<WebElement> links = Driver.get().findElements(By.tagName("a"));
        System.out.println("Number of Links in the Page is " + links.size());

        for (int i = 1; i <= links.size(); i++) {
            System.out.println("Name of Link# " + i + " : " +links.get(i).getText());
        }
        return links;
    }
    public static List<WebElement> getPageLinks() {
        List<WebElement> links = Driver.get().findElements(By.tagName("a"));
        System.out.println("Number of Links in the Page is " + links.size());

        for (int i = 1; i < links.size(); i++) {
            System.out.println("Name of Link# " + i + " : " + links.get(i).getText());
        }
        return links;
    }

    /**
     * Extracts text from list of elements matching the provided locator into new List<String>
     *
     * @param locator
     * @return list of strings
     */
    public static List<String> getElementsText(By locator) {

        List<WebElement> elems = Driver.get().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }

    /**
     * Performs a pause
     * @author US
     * @param seconds
     */
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * Performs a pause with implicit wait
     * @author US
     * @param seconds
     */
    public static void waitImplicit(int seconds){
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    /**
     * Performs a pause with explicit wait
     * @author US
     * @param webElement,seconds
     */
    public static void waitExplicit(WebElement webElement, int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.get(),Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }
    /**
     * Performs a pause with explicit wait
     * @author US
     * @param seconds
     */
    public static void waitExplicit(int seconds){
        WebDriverWait wait = new WebDriverWait(Driver.get(),Duration.ofSeconds(seconds));
    }
    /**
     * Scrolls down to an element using JavaScript
     *
     * @param element
     */
    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Performs double click action on an element
     *
     * @param element
     */
    public static void doubleClick(WebElement element) {
        new Actions(Driver.get()).doubleClick(element).build().perform();
    }

    /**
     * Moves the mouse ti given element
     * @author US
     * @param element
     */
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.get());
        actions.moveToElement(element).perform();
    }

    /**
     * Highlighs an element by changing its background and border color
     * @param element
     */
    public static void highlight(WebElement element) {
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
        waitFor(1);
        ((JavascriptExecutor) Driver.get()).executeScript("arguments[0].removeAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    public static void waitForClickability(WebElement element, int timeout){
        WebDriverWait wait= new WebDriverWait(Driver.get(), Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * Changes to window to the dashboard
     * @author US
     */
    public static void switchToWindow(){
        String currentTab = Driver.get().getWindowHandle();
        Set<String> allTabs = Driver.get().getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(currentTab)){
                Driver.get().switchTo().window(tab);
            }
        }
    }

    /**
    Takes a screenshot for entire page
     */
    public static void takeScreenshotForAllPage() throws IOException {
        File scrFile = ((TakesScreenshot)Driver.get()).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./fullPage.png"));
    }

    /**
     * Takes a screenshot for a Webelement
     * @author US
     */
    public static void takeScreenshotForWebElement(WebElement webElementName) {
        try {
            File scrFile = webElementName.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("./"+webElementName+".png"));
        } catch (WebDriverException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Takes performance logs
     * @author US
     */
    public static void getPerformanceLogs(){
        for (LogEntry entry : Driver.get().manage().logs().get(LogType.PERFORMANCE)) {
            System.out.println(entry.toString());
        }
    }

    /**
     * Takes browser logs
     * @author US
     */
    public static void getBrowserLogs(){
        for (LogEntry entry : Driver.get().manage().logs().get(LogType.BROWSER)) {
            System.out.println(entry.toString());
        }
    }

    /**
     * Takes client logs
     * @author US
     */
    public static void getClientLogs(){
        for (LogEntry entry : Driver.get().manage().logs().get(LogType.CLIENT)) {
            System.out.println(entry.toString());
        }
    }

    /**
     * It directly goes to SearchAds dashboard
     * @author US
     */
    public static void switchToSearchAdsDashboard(){
//        String origin = Driver.get().getWindowHandle();
        String origin = "Ads Manager - SearchAds.com - #1 Apple Search Ads Campaign Management and Intelligence Tool";
        for (String handle : Driver.get().getWindowHandles()){
            Driver.get().switchTo().window(handle);
            if (Driver.get().getTitle().equals(origin)){
                return;
            }
        }

    }
    public static void waitForPageToLoad(int timeoutInSeconds){
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>(){
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.get(),Duration.ofSeconds(25));
            wait.until(expectation);
        }catch (Throwable error){
            error.printStackTrace();;
        }
    }

    public static String captureScreenShot(){
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.get();
        String basecode64 = takesScreenshot.getScreenshotAs(OutputType.BASE64);
        return basecode64;
    }


}
