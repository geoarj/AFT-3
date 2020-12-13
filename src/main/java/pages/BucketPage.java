package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Product;

import static managers.DriverManager.getDriver;

public class BucketPage extends BasePage {

    @FindBy(xpath = ".//span[@class=\"base-ui-radio-button__icon base-ui-radio-button__icon_checked\"]")
    WebElement guaranteePeriod;

    //    проверить, что для приставки выбрана гарантия на 2 года
    public BucketPage checkGuaranteePeriod() {
        Assert.assertTrue(guaranteePeriod.getText().contains("24"));
        return app.getBucketPage();
    }

    //    проверить цену каждого из товаров и сумму
    public BucketPage checkThePrice()  {
        int fullPrice = atoi(getDriver().findElement(By.xpath("//div[@class = 'buttons']//span[@class = 'cart-link__price']")).getText());
        Assert.assertEquals(fullPrice, Product.list.get(1).getPrice() + Product.list.get(2).getPrice());
        return app.getBucketPage();
    }

    //     удалить из корзины Detroit
    public BucketPage removeElementFromCart() {
        getDriver().findElement(By.xpath("//a[text() = 'Игра Detroit: Стать человеком (PS4)']/../../..//button[text() = 'Удалить']")).click();
        sleepByInterval(1000);
        checkIfElementIsNotInCartAndPriceDecreased();
        Product.list.remove(Product.list.size() - 1);
        return app.getBucketPage();
    }

    //     проверить что Detroit нет больше в корзине и что сумма уменьшилась на цену Detroit
    private void checkIfElementIsNotInCartAndPriceDecreased() {
        int fullPrice = atoi(getDriver().findElement(By.xpath("//div[@class = 'buttons']//span[@class = 'cart-link__price']")).getText());
        Assert.assertEquals(fullPrice, Product.list.get(1).getPrice());
    }

    //    добавить еще 2 playstation (кнопкой +) и проверить что сумма верна (равна трем ценам playstation)
    public BucketPage addMoreProductsToCart() {
        for (int i = 0; i < 3; i++) {
            getDriver().findElement(By.xpath("//i[@class = 'count-buttons__icon-plus']")).click();
            sleepByInterval(1000);
        }
        for (Product p : Product.list) {
            System.out.println(p.getPrice() + " " + p.getName());
        }

        Product.list.add(Product.list.get(1));
        Product.list.add(Product.list.get(1));
        checkThePriceInList();
        return app.getBucketPage();
    }

    public BucketPage returnDeletedProduct(String name, int price) {
        scrollToElementJs(getDriver().findElement(By.xpath("//li[@class = 'header-top-menu__info']")));
        getDriver().findElement(By.xpath("//span//span[@class = 'restore-last-removed']")).click();
        Product.list.add(new Product(name, price));
        checkThePriceInList();
        return this;
    }
}
