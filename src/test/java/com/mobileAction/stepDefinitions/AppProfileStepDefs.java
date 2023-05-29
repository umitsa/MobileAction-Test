package com.mobileAction.stepDefinitions;

import com.mobileAction.pages.appIntelligence.AppProfile;
import com.mobileAction.utils.BrowserUtils;
import com.mobileAction.utils.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppProfileStepDefs {

    AppProfile appProfile = new AppProfile();
    WebDriverWait wait=new WebDriverWait(Driver.get(), Duration.ofSeconds(10));

    @When("the user search an {string} by id or name")
    public void the_user_search_an_by_id_or_name(String appName) {
        wait.until(ExpectedConditions.elementToBeClickable(appProfile.searchInputBox));
        appProfile.searchInputBox.sendKeys(appName+ Keys.ENTER);
        BrowserUtils.waitFor(3);
    }
    @Then("visibility score is shown on the app profile page")
    public void visibility_score_is_shown_on_the_app_profile_page() {
        BrowserUtils.waitExplicit(appProfile.appProfileVisibilityScore,5);
        System.out.println("The Visibility Score of the " +
                appProfile.currentAppName.getText() + " : "+appProfile.appProfileVisibilityScore.getText());
    }
}
