package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.GeohackPage_Kollex;
import pages.GoogleMapsPage;
import pages.WikipediaPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Test_Kollex {

    WebDriver driver;
    WikipediaPage objWikipediaPage;
    GoogleMapsPage objGoogleMapsPage;
    GeohackPage_Kollex objgeohackPage_Kollex;

    String coordinates;


    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver 11.30.16");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String baseUrl = "http://www.google.com/";
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://wikipedia.com/");
    }

    @Test(priority = 1)
    public void wikiSearch() throws IOException {
        objWikipediaPage = new WikipediaPage(driver);
        objWikipediaPage.fillInSearch("Giga Berlin");
       // objWikipediaPage.clickOnSearchBtn();
        // Save coordinates from Wikipedia portal
        coordinates = objWikipediaPage.findCoordinatesAndSave();
        ///////test coordinates google map
        driver.get("https://maps.google.com/");
        objGoogleMapsPage = new GoogleMapsPage(driver);
        objGoogleMapsPage.enterSearchName("Tesla Gigafactory Berlin-Brandenburg Gigafactory 4");
        objGoogleMapsPage.waitResultsContainerToBeLoaded();
        objGoogleMapsPage.checkCoordinatesInURL(coordinates);
        /////////////////

    }
    @Test(priority = 2)
    public void openGoogleMapsAndDoScreenshot() throws IOException {

        objWikipediaPage = new WikipediaPage(driver);
        driver.get("https://wikipedia.com/");
        objWikipediaPage.fillInSearch("Giga Berlin");
//        objWikipediaPage.clickOnSearchBtn();
        objWikipediaPage.findLogistics();
        objWikipediaPage.findSiteConcerns();
        objWikipediaPage.openCoordinatesInNewTab();

        ///////////Switch to newly opened tab with GeoHack
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); //switches to new tab
        ///////////////////////////
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//        }
        ///////////////////////////
        objgeohackPage_Kollex = new GeohackPage_Kollex(driver);
        objgeohackPage_Kollex.clickGoogleMapsIcon();
        //////////////Switch to new tab with google maps
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(2));
        ///////////////////////////
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        ///////////////////////////

    }


//    @Test(priority = 2)
//    public void inspectCoordinates() {
//        driver.get("https://maps.google.com/");
//        objGoogleMapsPage = new GoogleMapsPage(driver);
//    //????????
//    driver.switchTo().defaultContent();
//    //????????
//        objGoogleMapsPage.enterSearchName("Tesla Gigafactory Berlin-Brandenburg Gigafactory 4");
//        objGoogleMapsPage.waitResultsContainerToBeLoaded();
//        objGoogleMapsPage.checkCoordinatesInURL(coordinates);
//    }

    @AfterTest
    public void shutDownSelenium() throws IOException {

        /////Take screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        FileUtils.copyFile(scrFile, new File("\\screenshotTEST.png"));
        /////
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        /////End Take screenshot

        driver.quit();
    }
}
