package com.tests;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {

        // BROWSER ==> chrome/firefox
        // HUB_HOST ==> localhost/ 10.0.1.3 /hostname
        //String host = "selenium4.auzmor.com";
        String host = "localhost";
        MutableCapabilities dc;

        if(System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
            dc = new FirefoxOptions();
            dc.setCapability("se:recordVideo","true");
        }else{
            dc = new ChromeOptions();
            dc.setCapability("se:recordVideo","true");
        }

        if(System.getProperty("HUB_HOST") != null){
            host = System.getProperty("HUB_HOST");
        }

        String testName = ctx.getCurrentXmlTest().getName();

        String completeUrl = "http://" + host + "/wd/hub";
        dc.setCapability("name",testName);
        this.driver = new RemoteWebDriver(new URL(completeUrl),dc);
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
