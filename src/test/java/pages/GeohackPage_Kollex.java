package pages;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeohackPage_Kollex {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"mw-content-text\"]/div/div[2]/div[2]/p/a[2]/span/img")
    WebElement googleMapsIcon;

    public GeohackPage_Kollex(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickGoogleMapsIcon (){
        ////////Waiter
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(googleMapsIcon));
        ////////Waiter
        googleMapsIcon.click();
    }
}
