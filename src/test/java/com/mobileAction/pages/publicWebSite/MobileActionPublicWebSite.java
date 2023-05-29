package com.mobileAction.pages.publicWebSite;

import com.mobileAction.utils.BrowserUtils;
import com.mobileAction.utils.ConfigurationReader;
import com.mobileAction.utils.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MobileActionPublicWebSite {

    public MobileActionPublicWebSite(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(xpath = "//*[contains(text(),'Login')]")
    public WebElement loginLink;
    @FindBy(id = "username")
    public WebElement emailInputBox;

    @FindBy (id = "password")
    public WebElement passwordInputBox;

    @FindBy(xpath = "//button[contains(.,'Login')]")
    public WebElement submitButton;

    public void loginWithCredentialOnPublicSite(String username)  {
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        MobileActionPublicWebSite mobileActionPublicWebSite = new MobileActionPublicWebSite();
        BrowserUtils.waitFor(3);
        try {
            emailInputBox.sendKeys(username+ Keys.ENTER);
            BrowserUtils.waitFor(2);
            passwordInputBox.sendKeys(ConfigurationReader.get("adminPassword1"));
            BrowserUtils.waitFor(2);
            submitButton.click();
            BrowserUtils.waitFor(3);
        } catch (Exception e) {
            System.out.println("Login page was skipped");
            throw new RuntimeException("Caches Active!");
        }
    }

}
