package com.mobileAction.pages.appIntelligence;

import com.mobileAction.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AppProfile {

    public AppProfile(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "rc_select_0")
    public WebElement searchInputBox;

    @FindBy(xpath = "(//h4[@class='ma-current-app-name m-0'])[1]")
    public WebElement currentAppName;


    @FindBy(className =  "ma-app-match-store-icon")
    public List<WebElement> iOSAndroidSelector;

    @FindBy(className = "rc-virtual-list-holder-inner")
    public WebElement allSearchList;

    @FindBy(xpath = "//div[@class='ma-visibility-score flex flex-row gap-2 items-center']//h4")
    public WebElement appProfileVisibilityScore;





}
