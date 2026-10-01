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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> products = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(productsList)
        );
        return products.size();
    }

    public boolean areProductsNamesAndPricesVisible() {
        List<WebElement> products = driver.findElements(productsList);

        if (products.isEmpty()) {
            return false;
        }

        for (WebElement product : products) {
            // Encontrar el título y el precio dentro del contexto de la tarjeta actual
            WebElement nameElement = product.findElement(productNameLocator);
            WebElement priceElement = product.findElement(productPriceLocator);

            // Validar que ambos elementos estén visibles y no estén vacíos
            boolean isNameValid = nameElement.isDisplayed() && !nameElement.getText().trim().isEmpty();
            boolean isPriceValid = priceElement.isDisplayed() && !priceElement.getText().trim().isEmpty();

            if (!isNameValid || !isPriceValid) {
                return false;
            }
        }
        return true;
    }

    public void addProductToCart(String productName, int quantity) {
        String xpathExpression = String.format(
                "//article[contains(@class, 'product-card')][.//h3[text()='%s']]//button",
                productName
        );

        By addToCartButton = By.xpath(xpathExpression);

        for (int i = 0; i < quantity; i++) {
            WebElement buttonElement = driver.findElement(addToCartButton);

            // Hace scroll al centro para evitar que el header bloquee el clic
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", buttonElement);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(buttonElement));

            try {
                buttonElement.click();
            } catch (Exception e) {
                // Clic fallback por JavaScript si se intercepta el clic nativo
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buttonElement);
            }
        }
    }

    public void addProductToCartMultipleTimes(String productName, int times) {
        for (int i = 0; i < times; i++) {
            addProductToCart(productName,times);
        }
    }

    public String getCartCounterText() {
        return getText(cartBadge);
    }

    public String getCartTotalAmount() {
        return getText(cartTotal);
    }
    public void selectStoreCategoryCard(String categoryName) {
        By categoryCard = By.xpath("//nav[@aria-label='Módulos']//span[text()='" + categoryName + "']");
        //  click(categoryCard);
        WebElement element = driver.findElement(categoryCard);

        // 1. Scroll para centrar la tarjeta en la pantalla
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        // 2. Intentar el clic normal; si falla por bloqueo, hacer el clic por JS
        try {
            click(categoryCard);
        } catch (ElementClickInterceptedException e) {
            js.executeScript("arguments[0].click();", element);
        }
    }
    public void removeProductFromCart(String productName) {
        String xpathExpression = String.format(
                "//div[@id='cart-items']//button[contains(@aria-label, 'Quitar %s') or contains(@title, 'Quitar %s')]",
                productName, productName
        );
        click(By.xpath(xpathExpression));
    }
    public void enterShippingAddress(String addr) {
        type(addressInput, addr);
    }
    public void enterCardNumber(String cardNumber) {
        type(cardNumberInput, cardNumber);
    }
    public void clickConfirmOrder() {
        click(confirmOrderButton);
    }
    public String getErrorMessage() {
        WebElement addressElement = driver.findElement(addressInput);
        String html5AddressError = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", addressElement);

        if (html5AddressError != null && !html5AddressError.isEmpty()) {
            return html5AddressError;
        }

        WebElement cardElement = driver.findElement(cardNumberInput);
        String html5CardError = (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", cardElement);

        if (html5CardError != null && !html5CardError.isEmpty()) {
            return html5CardError;
        }

        return getText(errorMessage);
    }
    public boolean isOrderConfirmationDisplayed() {
        return isDisplayed(confirmationMessage);
    }

    /**
     * Retorna true si el botón de "Confirmar pedido" sigue estando visible.
     */
    public boolean isConfirmButtonDisplayed() {
        return isDisplayed(confirmOrderButton);
    }

}
