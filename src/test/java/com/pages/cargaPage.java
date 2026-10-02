package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class cargaPage extends BasePage {
    // Locadores extraídos del DOM real de la app
    private final By loadButton = By.id("load-element");
    private final By loadTargetContainer = By.id("load-target");
    private final By loadedDynamicButton = By.xpath("//div[@id='load-target']//button[contains(@id,'loaded-')]");

    // Locadores basados en los elementos del ejercicio
    private final By inputField = By.xpath("//input[@type='text' or contains(@placeholder,'escribir')]");

    // Locadores interfaz "Campo diferido" y manipulación DOM
    private final By enableFieldButton = By.id("enable-field");
    private final By deferredInput = By.id("delayed-input");
    private final By Btnremplazar = By.id("replace-element");
    private final By Btnremove = By.id("remove-element");
    private final By Btntarget = By.xpath("//p[@class='stale-target']");

    private WebElement savedPreviousElement;

    // Locadores tarjeta "04 / API HTTP - Latencia y errores"
    private final By delayInput = By.id("simulate-delay");
    private final By httpStatusSelect = By.id("simulate-status");
    private final By sendRequestButton = By.xpath("//button[contains(@class,'button-secondary') and text()='Enviar petición']");
    private final By responseStatusResult = By.id("simulate-result");

    private long lastElapsedTime = 0;

    public void selectModule(String moduleName) {
        By moduleTab = By.xpath("//*[contains(text(),'" + moduleName + "')]");
        click(moduleTab);
    }

    public void clickLoadButton() {
        click(loadButton);
    }

    public boolean verifySpinnerDuration() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        return wait.until(ExpectedConditions.attributeContains(loadTargetContainer, "aria-live", "polite"));
    }

    public String getLoadedButtonId() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(loadedDynamicButton));
        return button.getAttribute("id");
    }

    public void clickLoadedButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(loadedDynamicButton)).click();
    }

    public String getConfirmationText() {
        return getText(loadTargetContainer);
    }

    public boolean isInputFieldDisabled() {
        WebElement field = driver.findElement(inputField);
        return !field.isEnabled();
    }

    public boolean isDeferredInputDisabled() {
        WebElement element = driver.findElement(deferredInput);
        return !element.isEnabled();
    }

    public void clickEnableFieldButton(String buttonName) {
        switch (buttonName) {
            case "Habilitar campo":
                click(enableFieldButton);
                break;
            case "Reemplazar":
                savedPreviousElement = driver.findElement(Btntarget);
                click(Btnremplazar);
                break;
            case "Eliminar":
                click(Btnremove);
                break;
            case "Enviar petición":
                sendRequestAndMeasureTime();
                break;
            default:
                throw new IllegalArgumentException("Botón no reconocido: " + buttonName);
        }
    }

    public boolean waitForDeferredInputToBeEnabled() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(deferredInput));
        return element.isEnabled();
    }

    public void typeInDeferredInput(String text) {
        type(deferredInput, text);
    }

    public String getDeferredInputValue() {
        return driver.findElement(deferredInput).getAttribute("value");
    }

    public String getUpdatedNodeText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement newNode = wait.until(ExpectedConditions.visibilityOfElementLocated(Btntarget));
        return newNode.getText();
    }

    public boolean isPreviousNodeStale() {
        if (savedPreviousElement == null) {
            return false;
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.stalenessOf(savedPreviousElement));
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            return true;
        }
    }

    public boolean isNodeRemoved() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(Btntarget));
    }

    public void setDelay(String delayMs) {
        WebElement input = driver.findElement(delayInput);
        input.clear();
        input.sendKeys(delayMs);
    }

    public void selectHttpStatus(String statusText) {
        Select select = new Select(driver.findElement(httpStatusSelect));
        select.selectByVisibleText(statusText);
    }

    /**
     * Limpia el resultado previo en la UI, hace clic en 'Enviar petición'
     * y mide el tiempo exacto hasta la renderización de la nueva respuesta.
     */
    public long sendRequestAndMeasureTime() {
        WebElement resultElement = driver.findElement(responseStatusResult);

        long startTime = System.currentTimeMillis();
        click(sendRequestButton);

        // Esperar hasta que el texto cambie y ya no sea el mensaje provisional de carga
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(driver1 -> {
            String currentText = resultElement.getText().trim();
            return !currentText.isEmpty()
                    && !currentText.equalsIgnoreCase("Esperando respuesta...")
                    && !currentText.contains("Cargando");
        });

        long endTime = System.currentTimeMillis();
        this.lastElapsedTime = (endTime - startTime);
        return this.lastElapsedTime;
    }

    public String getSimulateResultText() {
        return driver.findElement(responseStatusResult).getText();
    }

    public String getDelayValidationMessage() {
        WebElement input = driver.findElement(delayInput);
        return input.getAttribute("validationMessage");
    }

    public long getLastElapsedTime() {
        return lastElapsedTime;
    }
}