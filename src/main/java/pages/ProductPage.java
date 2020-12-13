package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Product;

public class ProductPage extends BasePage {

    @FindBy(xpath = "//span[contains(@class,'product-card-price__current')]")
    WebElement price;

    @FindBy(xpath = "//h1")
    WebElement nameProduct;

    @FindBy(xpath = "//select[@class ='form-control']")
    WebElement warranty;

    @FindBy(xpath = "//option[@value = '1']")
    WebElement optionWarranty;

    @FindBy(xpath = "//button[@class='button-ui button-ui_brand buy-btn btn-cart button-ui_buy-card button-ui_passive']")
    WebElement buttonBuy;

    @FindBy(xpath = "//div[@class = 'buttons']//span[@class = 'cart-link__price']")
    WebElement cartPrice;

    @FindBy(xpath = "//div[@class = 'buttons']//span[@class = 'cart-link__badge']")
    WebElement countProductInCart;

    int countProduct = 0;

    // 4 запомнить цену
    public ProductPage rememberPrice() {
        int price = atoi(waitUtilElementToBeVisible(this.price).getText());
        String name = nameProduct.getText();
        Product.list.add(new Product(name, price));
        return app.getProductPage();
    }

    //5 доп. гарантия - выбрать 2 годв
    public ProductPage selectWarranty() {
        waitUntilElementToBeClickable(warranty);
        warranty.click();
        waitUntilElementToBeClickable(optionWarranty);
        optionWarranty.click();
        return app.getProductPage();
    }

    //6 дождаться изменения цены и запомнить цену с гарантией
    public ProductPage waitForPriceToBeChanged() {
        rememberPrice();
        return app.getProductPage();
    }

    //7 нажать купить
    public ProductPage pressBuy() {
        waitUntilElementToBeClickable(buttonBuy);
        buttonBuy.click();
        int i = 0;
        while (i < 5) {
            if (Integer.parseInt(countProductInCart.getText()) == countProduct + 1) {
                countProduct++;
                return this;
            }
            sleepByInterval(500);
            i++;
        }
        Assert.fail("Product was not added");
        return app.getProductPage();
    }

    //8 проверить что цена корзины стала равна сумме покупок
    public ProductPage checkPriceInCart() {
        int fullPrice = atoi(cartPrice.getText());
        Assert.assertEquals(fullPrice, Product.list.get(1).getPrice() + Product.list.get(2).getPrice());
        return app.getProductPage();
    }

    //проверять подлинность / открытие страницы
    public ProductPage checkOpenProductPage(String name) {
        int i = 0;
        while (i < 5) {
            if (nameProduct.getText().contains(name)) {
                return this;
            }
            sleepByInterval(500);
            i++;
        }
        Assert.fail("Page didn't load");
        return this;
    }

//    перейти в корзину
    public BucketPage goToTheCart() {
        waitUntilElementToBeClickable(cartPrice);
        cartPrice.click();
        return app.getBucketPage();
    }

}

