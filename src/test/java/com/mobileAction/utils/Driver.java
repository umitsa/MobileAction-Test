package com.mobileAction.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {

    private Driver() {
    }
    // InheritableThreadLocal  --> this is like a container, pool.
    // in this pool we can have separate objects for each thread
    // for each thread, in InheritableThreadLocal we can have separate object for that thread
    // driver class will provide separate webdriver object per thread
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();
    public static WebDriver get() {
        //if this thread doesn't have driver - create it and add to pool
        if (driverPool.get() == null) {
//            if we pass the driver from terminal then use that one
//           if we do not pass the driver from terminal then use the one properties file
            String browser = System.getProperty("browser") != null ? browser = System.getProperty("browser") : ConfigurationReader.get("browser");
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options1 = new ChromeOptions();
                    options1.setPageLoadStrategy(PageLoadStrategy.EAGER);
                    options1.setCapability("browserVersion", "113.0");
                    options1.addArguments("--disable-popup-blocking");
                    options1.addArguments("--disable-extensions");
                    options1.addArguments("--disable-notifications");
                    options1.addArguments("--ignore-certificate-errors");
                    options1.addArguments("--disable-web-security");
                    options1.addArguments("--allow-running-insecure-content");
                    driverPool.set(new ChromeDriver(options1));
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "chromeSSL":
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
    //                options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                    options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "remote_chrome":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("start-maximized");
                    chromeOptions.setCapability("platform", Platform.ANY);
                    try {
                        driverPool.set(new RemoteWebDriver(new URL("http://3.92.205.75:4444/wd/hub"),chromeOptions));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "safari":
                    if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                        throw new WebDriverException("Your OS doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driverPool.set(new SafariDriver());
                    break;

            }
        }
        return driverPool.get();
    }
    public static void closeDriver() {
        driverPool.get().quit();
        driverPool.remove();
    }
}
