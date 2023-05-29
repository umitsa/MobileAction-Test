package com.mobileAction.stepDefinitions;

import com.mobileAction.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Hooks {


    @Before
    public void setUp(){
        System.out.println("Hooks Run!");
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        Driver.get().manage().window().maximize();

    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        System.out.println("Hooks Closed!");
        if(scenario.isFailed()){
            if (Driver.get() instanceof ChromeDriver) {
                final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot,"image/png", "screenshot");
            } else if (Driver.get() instanceof FirefoxDriver) {
                File fullPage = ((FirefoxDriver) Driver.get()).getFullPageScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(fullPage, new File("./screenshot/fullpage.jpg"));
            }
        }
        Driver.get().manage().deleteAllCookies();
//        BrowserUtils.getPerformanceLogs();
        Driver.closeDriver();
    }
}
