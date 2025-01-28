package com.wise.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.wise.actionsDriver.Action;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    public static Properties prop;
    public static WebDriver driver;

    @BeforeTest
    public void loadConfig() {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//Configuration//Config");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  @BeforeMethod
    public void launchBrowser() {
       String browserName = prop.getProperty("browser");
        if (browserName == null) {
            throw new IllegalArgumentException("Browser name is not specified in the properties file.");
        }

        if (browserName.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
       
        Action.maximizeBrowser();
        Action.pageLoadTimeOut(driver, 50);
        driver.get(prop.getProperty("URL"));
    }
//    @BeforeMethod
//    @Parameters("browser")
//    public void launchBrowser(String browserName) {
//       // String browserName = prop.getProperty("browser");
//        if (browserName == null) {
//            throw new IllegalArgumentException("Browser name is not specified in the properties file.");
//        }
//
//        if (browserName.equalsIgnoreCase("Chrome")) {
//            WebDriverManager.chromedriver().setup();
//            driver = new ChromeDriver();
//        } else if (browserName.equalsIgnoreCase("Edge")) {
//            driver = new EdgeDriver();
//        } else {
//            throw new IllegalArgumentException("Unsupported browser: " + browserName);
//        }
//       
//        Action.maximizeBrowser();
//        Action.pageLoadTimeOut(driver, 50);
//        driver.get(prop.getProperty("URL"));
//    }
    @BeforeMethod
    public WebDriver getDriver() {
        return driver;
    }
@AfterMethod
    public static void quit() {
        if (driver != null) {
            driver.quit();
       }
    }
}
