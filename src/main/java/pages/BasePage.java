package pages;

import managers.PageManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Product;

import java.util.List;

import static managers.DriverManager.getDriver;

public class BasePage {

    @FindBy(xpath = "//nav//input")
    protected WebElement searchElement;

    @FindBy(xpath = "//nav//span[contains(@class,'icon_search')]")
    protected WebElement searchButton;

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 10, 1000);

    protected PageManager app = PageManager.getManagerPages();

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriverWait getWait() {
        return wait;
    }

    protected void scrollToElementJs(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void waitUntilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected WebElement waitUtilElementToBeVisible(WebElement element) {
         wait.until(ExpectedConditions.visibilityOf(element));
         return element;
    }

    protected int atoi(String str) {
        str = str.replaceAll("\\D", "");
        return Integer.parseInt(str);
    }

    public ResultsPage selectSearchInResultPage(String nameOfSearch) {
        selectSearch(nameOfSearch);
        return app.getResultsPage();
    }

    public ProductPage selectSearchInProductPage(String nameOfSearch) {
        selectSearch(nameOfSearch);
        return app.getProductPage().checkOpenProductPage(nameOfSearch);
    }

    private void selectSearch(String nameOfSearch) {
        waitUntilElementToBeClickable(searchElement);
        searchElement.click();
        searchElement.sendKeys(nameOfSearch);
        searchButton.click();
    }

    public void sleepByInterval(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkThePriceInList() {
        int n = 0;
        for (int i = 1; i < Product.list.size(); i++) {
            n += Product.list.get(i).getPrice();
        }
        sleepByInterval(2000);
        int fullPrice = atoi(getDriver().findElement(By.xpath("//div[@class = 'buttons']//span[@class = 'cart-link__price']")).getText());
        Assert.assertEquals(fullPrice, n);
    }

}
