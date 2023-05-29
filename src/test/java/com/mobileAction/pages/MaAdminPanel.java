package com.mobileAction.pages;

import com.mobileAction.utils.Driver;
import com.mobileAction.utils.BrowserUtils;
import com.mobileAction.utils.ConfigurationReader;
import com.mobileAction.utils.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MaAdminPanel {
    public MaAdminPanel(){
        PageFactory.initElements(Driver.get(), this);
    }
    @FindBy(xpath = "//div[text()='User Name']/../div[2]/div/div/input")
    //@FindBy(xpath = "//tbody/tr[1]/td[2]/div[1]/div[1]/div[2]/div[1]/div[1]/input[1]")
    public WebElement userNameInputBoxAdminPanel;

    @FindBy(xpath = "//*[@data-icon='rocket']")
    public WebElement mobileActionIcon;

    @FindBy(xpath = "//main//div[@class='ma-action-buttons dx-template-wrapper']/div[2]")
    public WebElement searchAdsIcon;

    @FindBy(xpath = "//div[@data-dx_placeholder='Filter by Account Id']")
    public WebElement accountIDInputBox;

    //@FindBy (xpath = "//span[. = 'OK']")
    @FindBy (xpath = "//body[1]/div[7]/div[1]/div[3]/button[2]/span[1]")
    public WebElement popupOK;

//    @FindBy (xpath = "//input[@type='email']")
    @FindBy (id = "identifierId")
    public WebElement emailInputBoxSignIn;

    //@FindBy (xpath = "//input[@placeholder = 'Enter your password']")
    @FindBy (xpath = "//input[@type='password']")
    public WebElement passwordInputBoxSignIn;

    //@FindBy (xpath = "(//button[contains(.,'Login')])[1]")
    @FindBy (xpath = "//button[. = ' Login ']")
    public WebElement loginButton;

    @FindBy (xpath = "//div[span='POC - User Accounts']")
    public WebElement panelPageTitle;

    @FindBy (xpath = "//*[text()=' Login with Google ']")
    public WebElement loginWithGoogleButton;

    public void impersonateWithUserName(String userName){
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        if (this.loginWithGoogleButton.isEnabled()){
            loginWithGoogleButton.click();
            BrowserUtils.waitExplicit(3);
        }
        userNameInputBoxAdminPanel.click();
        userNameInputBoxAdminPanel.sendKeys(userName+ Keys.ENTER);
        BrowserUtils.waitFor(3);
        searchAdsIcon.click();
        BrowserUtils.waitFor(2);
        popupOK.click();

    }

    public void impersonateWithAccountID(String accountID){
        accountIDInputBox.click();
        accountIDInputBox.sendKeys(accountID+Keys.ENTER);
        BrowserUtils.waitFor(3);
        searchAdsIcon.click();
        popupOK.click();
    }

    public void loginMaTestPanel(){
        emailInputBoxSignIn.sendKeys(ConfigurationReader.get("adminUser"));
        passwordInputBoxSignIn.sendKeys(ConfigurationReader.get("password1"));
        loginButton.click();
        BrowserUtils.waitFor(5);
    }
    public void loginMaAdminPanel(){
        loginWithGoogleButton.click();
        BrowserUtils.waitFor(1);
        emailInputBoxSignIn.sendKeys(ConfigurationReader.get("adminUser")+Keys.ENTER);
        BrowserUtils.waitExplicit(passwordInputBoxSignIn, 2);
        passwordInputBoxSignIn.sendKeys(ConfigurationReader.get("adminPassword")+Keys.ENTER);
        BrowserUtils.waitFor(5);
    }
}
