package com.mobileAction.stepDefinitions;

import com.mobileAction.pages.publicWebSite.MobileActionPublicWebSite;
import com.mobileAction.utils.BrowserUtils;
import com.mobileAction.utils.ConfigurationReader;
import com.mobileAction.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class LoginStepDefs {

    MobileActionPublicWebSite mobileActionPublicWebSite = new MobileActionPublicWebSite();
    WebDriverWait wait=new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
    @Given("the user navigate to mobileaction.co")
    public void the_user_navigate_to_mobileaction_co() {
        try {
            Driver.get().get(ConfigurationReader.get("MAPublicUrl"));
        } catch (Exception e) {
            System.out.println("The URL could be changed");
            throw new RuntimeException(e);
        }
        Driver.get().manage().window().maximize();
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    @When("the user clicks on login on the main page")
    public void the_user_clicks_on_login_on_the_main_page() {
        String currentTab = Driver.get().getWindowHandle();
        mobileActionPublicWebSite.loginLink.click();
        Set<String> allTabs = Driver.get().getWindowHandles();
        for (String tab : allTabs) {
            if (!tab.equals(currentTab)) Driver.get().switchTo().window(tab);
        }
        wait.until(ExpectedConditions.urlContains("https://insights.mobileaction.co/login"));
        BrowserUtils.waitFor(2);
    }
    @Then("the {string} login with public site")
    public void the_login_with_public_site(String username) {
        if (Driver.get().getCurrentUrl().contains("https://insights.mobileaction.co/login")){
            mobileActionPublicWebSite.loginWithCredentialOnPublicSite(username);
            BrowserUtils.waitFor(5);
        }else {
            System.out.println("URL: " + Driver.get().getCurrentUrl());
            BrowserUtils.waitFor(5);
            //BrowserUtils.getPerformanceLogs();
        }

    }
    @Then("mobileaction dashboard page is shown")
    public void mobileaction_dashboard_page_is_shown() throws InterruptedException {
        wait.until(ExpectedConditions.urlContains("insights.mobileaction.co/"));
 /*       try {
            if (saDashboard.notNowButton.isDisplayed()) {
                BrowserUtils.switchToSearchAdsDashboard();
                saDashboard.notNowButton.click();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
*/
        String actualPageTitle = Driver.get().getTitle();
        String expectedPageTitle = "MobileAction | App Profile";
        Assert.assertTrue(actualPageTitle.contains(expectedPageTitle));
        Thread.sleep(2000);
    }
}
