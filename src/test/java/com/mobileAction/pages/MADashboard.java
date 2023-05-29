package com.mobileAction.pages;

import com.mobileAction.utils.Driver;
import org.openqa.selenium.support.PageFactory;


public class MADashboard {

    public MADashboard(){
        PageFactory.initElements(Driver.get(), this);
    }



}
