package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class authenticationPage extends BasePage{

    private final By userNameInput = By.id("login-username");
    private final By passwordInput = By.id("login-password");
    private final By ButtonEnter = By.xpath("//button[contains(@class,'button-primary') and text() ='Entrar']");
    // Mensajes de validación y confirmación
    private final By errorMessage = By.id("login-result");
    private final By loginResult = By.id("login-result");

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
    public void fillLoginForm(String userName, String password) {
        WebElement uName = waitForVisibility(userNameInput);
        uName.clear();
        uName.sendKeys(userName);

        WebElement pwd = waitForVisibility(passwordInput);
        pwd.clear();
        pwd.sendKeys(password);
    }
    public void clickButtonByName() {
        click(ButtonEnter);

    }
    public String getErrorMessage() {

        return getText(errorMessage);
    }
    public String getLoginResultMessage(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        // Espera explícita hasta que el texto del elemento sea el esperado tras los 2s de delay
        wait.until(ExpectedConditions.textToBePresentInElementLocated(loginResult, expectedMessage));
        return getText(loginResult);
    }
    public String getAuthErrorMessage(String expectedMessage) {
        // 1. Validar si hay un mensaje de error HTML5 nativo por campos vacíos
        WebElement userInput = driver.findElement(userNameInput);
        String html5UserError = (String) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", userInput);

        if (html5UserError != null && !html5UserError.isEmpty()) {
            return html5UserError;
        }

        // 2. Esperar los 2 segundos de respuesta asíncrona hasta que aparezca el texto esperado en #login-result
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(loginResult, expectedMessage));

        return getText(loginResult);
    }
}
