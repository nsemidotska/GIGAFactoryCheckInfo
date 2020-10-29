package pages;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WikipediaPage {

    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"searchInput\"]")
    WebElement searchInput;

    @FindBy(xpath = "//*[@id=\"search-form\"]/fieldset/button/i")
    WebElement searchBtn;

    @FindBy(xpath = "//*[@id=\"toc\"]/ul/li[2]/ul/li[2]/a/span[2]")
    WebElement logisticsLink;

    @FindBy(xpath = "//*[@id=\"toc\"]/ul/li[2]/ul/li[3]/a/span[2]")
    WebElement siteConcernsLink;

    @FindBy(xpath = "//*[@id=\"mw-content-text\"]/div[1]/table[1]/tbody/tr[5]/td/span[1]/span/a/span[3]/span[1]")
    WebElement coordinatesLink;


    public WikipediaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillInSearch(String arg1) {
        ////////Waiter
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(searchInput));
        ////////Waiter
        searchInput.sendKeys(arg1);
        searchBtn.click();
    }

    public void clickOnSearchBtn() {
        ////////Waiter
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(searchBtn));
        ////////Waiter
        searchBtn.click();
    }

    public void findLogistics() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(logisticsLink));

        String getLogisticsLinkText =logisticsLink.getText();
        assertEquals(getLogisticsLinkText,"Logistics");
    }

    public void findSiteConcerns() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(siteConcernsLink));

        String getLogisticsLinkText = siteConcernsLink.getText();
        assertEquals(getLogisticsLinkText,"Site concerns");

    }

    public String findCoordinatesAndSave() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(coordinatesLink));

        return coordinatesLink.getText();
    }

    public void openCoordinatesInNewTab (){
        Actions act = new Actions(driver);
        act.keyDown(Keys.COMMAND).click(coordinatesLink).build().perform();
    }

}


