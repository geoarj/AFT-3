package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ResultsPage extends BasePage {

    @FindBy(xpath = "//div[@class=\"product-info__title-link\"]//a")
    protected List<WebElement> searchList;

    public ProductPage selectSearchElement(String nameOfSearchElement) {
        if (nameOfSearchElement.equals("")) {
            Assert.fail("Введите название товара.");
        }

        for (WebElement searchEl : searchList) {
            if (searchEl.getText().toLowerCase().contains(nameOfSearchElement.toLowerCase())) {
                waitUntilElementToBeClickable(searchEl);
                searchEl.click();
                return app.getProductPage();
            }
        }
        Assert.fail("Товар \"" + nameOfSearchElement + "\" не найден.");
        return app.getProductPage();
    }

}
