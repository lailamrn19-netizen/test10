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
        safeClick(categoryCard);
    }

    public void fillLoginForm(String userName, String password) {
        type(userNameInput, userName);
        type(passwordInput, password);
    }

    public void clickButtonByName() {
        safeClick(ButtonEnter);
    }

    public String getErrorMessage() {
        return getText(loginResult);
    }

    public String getLoginResultMessage(String expectedMessage) {
        return getTextWhenContains(loginResult, expectedMessage, 5);
    }

    public String getAuthErrorMessage(String expectedMessage) {
        // 1. Validar si hay un mensaje de error HTML5 nativo en el input de usuario
        String html5UserError = getHtml5ValidationMessage(userNameInput);
        if (!html5UserError.isEmpty()) {
            return html5UserError;
        }

        // 2. Validar si hay un mensaje de error HTML5 nativo en el input de contraseña
        String html5PassError = getHtml5ValidationMessage(passwordInput);
        if (!html5PassError.isEmpty()) {
            return html5PassError;
        }

        // 3. Esperar la respuesta asíncrona en #login-result
        return getTextWhenContains(loginResult, expectedMessage, 5);
    }
}
