package test;

import base.BaseTests;
import org.junit.Test;

public class ExampleScenarioTest extends BaseTests {
    @Test
    public void exampleScenario() {
        app.getStartPage()
                .selectSearchInResultPage("playstation")
                .selectSearchElement("slim")
                .rememberPrice()
                .selectWarranty()
                .waitForPriceToBeChanged()
                .pressBuy()
                .selectSearchInProductPage("Detroit")
                .rememberPrice()
                .pressBuy()
                .checkPriceInCart()
                .goToTheCart()
                .checkGuaranteePeriod()
                .checkThePrice()
                .removeElementFromCart()
                .addMoreProductsToCart()
                .returnDeletedProduct("Detroit", 2599)
        ;
    }

}
