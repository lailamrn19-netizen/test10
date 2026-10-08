package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {
    // Locadores del catálogo y carrito
    private final By productsList = By.xpath("//div[@id='product-grid']/article"); // Ajusta la clase según el DOM de tu app
    private final By productNameLocator = By.xpath(".//h3");
    private final By productPriceLocator = By.xpath(".//div[@class='product-bottom']/strong");
    private final By cartBadge = By.id("cart-count");
    private final By cartTotal = By.id("cart-total");
    // Campos de entrada y botón de confirmación
    private final By addressInput = By.id("checkout-address");
    private final By cardNumberInput = By.id("checkout-card");
    private final By confirmOrderButton = By.xpath("//button[contains(@class,'button-primary') and text() ='Confirmar pedido']");
    // Mensajes de validación y confirmación
    private final By errorMessage = By.xpath("//*[contains(@class,'error') or contains(@class,'alert-danger')]");
    private final By confirmationMessage = By.xpath("//*[contains(text(),'Pedido confirmado') or contains(text(),'Gracias por su compra') or contains(@class,'success')]");

    public int getProductCount() {
        return findElementsWithWait(productsList).size();
    }

    public boolean areProductsNamesAndPricesVisible() {
        List<WebElement> products = driver.findElements(productsList);

        if (products.isEmpty()) {
            return false;
        }

        for (WebElement product : products) {
            WebElement nameElement = product.findElement(productNameLocator);
            WebElement priceElement = product.findElement(productPriceLocator);

            boolean isNameValid = nameElement.isDisplayed() && !nameElement.getText().trim().isEmpty();
            boolean isPriceValid = priceElement.isDisplayed() && !priceElement.getText().trim().isEmpty();

            if (!isNameValid || !isPriceValid) {
                return false;
            }
        }
        return true;
    }

    public void addProductToCart(String productName, int quantity) {
        By addToCartButton = By.xpath(String.format(
                "//article[contains(@class, 'product-card')][.//h3[text()='%s']]//button",
                productName
        ));

        for (int i = 0; i < quantity; i++) {
            safeClick(addToCartButton);
        }
    }

    // Método corregido: antes llamaba recursivamente a addProductToCart(productName, times) en un bucle
    public void addProductToCartMultipleTimes(String productName, int times) {
        addProductToCart(productName, times);
    }

    public String getCartCounterText() {
        return getText(cartBadge);
    }

    public String getCartTotalAmount() {
        return getText(cartTotal);
    }

    public void selectStoreCategoryCard(String categoryName) {
        By categoryCard = By.xpath("//nav[@aria-label='Módulos']//span[text()='" + categoryName + "']");
        safeClick(categoryCard);
    }

    public void removeProductFromCart(String productName) {
        By removeButton = By.xpath(String.format(
                "//div[@id='cart-items']//button[contains(@aria-label, 'Quitar %s') or contains(@title, 'Quitar %s')]",
                productName, productName
        ));
        safeClick(removeButton);
    }

    public void enterShippingAddress(String addr) {
        type(addressInput, addr);
    }

    public void enterCardNumber(String cardNumber) {
        type(cardNumberInput, cardNumber);
    }

    public void clickConfirmOrder() {
        safeClick(confirmOrderButton);
    }

    public String getErrorMessage() {
        // 1. Validar errores HTML5 de dirección y tarjeta
        String addressError = getHtml5ValidationMessage(addressInput);
        if (!addressError.isEmpty()) return addressError;

        String cardError = getHtml5ValidationMessage(cardNumberInput);
        if (!cardError.isEmpty()) return cardError;

        // 2. Si no hay error HTML5, obtener el mensaje devuelto en el DOM
        return getText(errorMessage);
    }

    public boolean isOrderConfirmationDisplayed() {
        return isDisplayed(confirmationMessage);
    }

    public boolean isConfirmButtonDisplayed() {
        return isDisplayed(confirmOrderButton);
    }

}
