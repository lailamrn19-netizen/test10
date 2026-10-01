package com.steps;

import com.pages.BasePage;
import com.pages.CartPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CartSteps {
  private final CartPage CartPage = new CartPage();
  @When("the user selects the {string} card from the store main page")
  public void selectStoreCategoryCard(String categoryName) {
      CartPage.selectStoreCategoryCard(categoryName);
  }

  @Then("the catalog should display {int} products")
    public void theCatalogShouldDisplayProducts(int expectedCount) {
        int actualCount = CartPage.getProductCount();
        Assert.assertEquals(actualCount, expectedCount, "El número de productos en el catálogo no coincide.");
  }

  @And("each product should have a visible name and price")
    public void eachProductShouldHaveAVisibleNameAndPrice() {
      Assert.assertTrue(CartPage.areProductsNamesAndPricesVisible(),
              "Uno o más productos no muestran correctamente su nombre o precio en el catálogo.");
  }

  @And("the user adds the product {string} with Count {string} to the cart")
    public void theUserAddsTheProductWithCountToTheCart(String productName, String quantity) {
        int count = Integer.parseInt(quantity);
        CartPage.addProductToCart(productName, count);
  }

  @Then("the cart counter should display {string}")
    public void theCartCounterShouldDisplay(String expectedCount) {
        String actualCount = CartPage.getCartCounterText();
        Assert.assertEquals(actualCount, expectedCount, "El contador del carrito no coincide.");
  }

  @Then("the total cart amount should be {string}")
    public void theTotalCartAmountShouldBe(String expectedTotal) {
        String actualTotal = CartPage.getCartTotalAmount();
        Assert.assertEquals(actualTotal, expectedTotal, "El monto total del carrito no coincide.");
  }
  @When("the user removes the product {string} from the cart")
    public void theUserRemovesTheProductFromTheCart(String productName) {
        CartPage.removeProductFromCart(productName);
  }
  @And("the user enters the shipping address {string}")
    public void theUserEntersTheShippingAddress(String address) {
        CartPage.enterShippingAddress(address);
  }

  @And("the user enters the card number {string}")
    public void theUserEntersTheCardNumber(String cardNumber) {
        CartPage.enterCardNumber(cardNumber);
  }

  @And("the user attempts to confirm the order")
    public void theUserAttemptsToConfirmTheOrder() {
        CartPage.clickConfirmOrder();
  }

  @Then("an error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedError) {
        Assert.assertEquals(CartPage.getErrorMessage(), expectedError);
  }


  @Then("a confirmation message should be displayed")
    public void aConfirmationMessageShouldBeDisplayed() {
        Assert.assertTrue(CartPage.isOrderConfirmationDisplayed());
  }

    @Then("the order should not be processed")
    public void the_order_should_not_be_processed() {
        // Verifica que el mensaje de confirmación de pedido NO esté presente/visible
        Assert.assertFalse(CartPage.isOrderConfirmationDisplayed(),"El pedido fue procesado cuando no debería haberlo sido");

        // Opcional: También puedes verificar que sigues en la página/formulario del carrito
        Assert.assertTrue(CartPage.isConfirmButtonDisplayed(),"El botón de confirmar pedido ya no está visible");
    }
}
