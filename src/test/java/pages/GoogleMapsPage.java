package pages;

import org.openqa.selenium.*;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class GoogleMapsPage {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"searchboxinput\"]")
    WebElement searchInput;

    @FindBy(xpath = "//*[@id=\"pane\"]/div/div[1]/div/div/div[2]/div[1]")
    WebElement resultContainer;

    @FindBy(xpath = "//*[@id=\"introAgreeButton\"]/span/span")
    WebElement iAcceptBtn;

    public GoogleMapsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickAcceptBtn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(iAcceptBtn));
        iAcceptBtn.click();
    }

    public void enterSearchName(String searchName) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        searchInput.sendKeys(searchName);
        searchInput.sendKeys(Keys.ENTER);
    }

    public void waitResultsContainerToBeLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(resultContainer));
    }



    // Method for parse URL to check coordinates
    public void checkCoordinatesInURL(String coordinates) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }

        String currentURLAfterSearch = driver.getCurrentUrl();
        String[] coordinatesFromUrl = currentURLAfterSearch.substring(currentURLAfterSearch.lastIndexOf("@") + 1).split(",");

        for (int i = 0; i < 2; i++) {
            String parsedSavedCoordinates = String.format("%.1f", Double.parseDouble(coordinates.split(" ")[i].replaceAll("[^\\d.]", "")));
            String parsedCurrentCoordinates = String.format("%.1f", (Double.parseDouble(coordinatesFromUrl[i])));

            assertEquals(parsedSavedCoordinates, parsedCurrentCoordinates);
        }
    }


}
