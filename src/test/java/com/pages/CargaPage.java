package com.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.pages.Locators.*;

import java.time.Duration;

public class CargaPage extends BasePage {
    private WebElement savedPreviousElement;

    private long lastElapsedTime = 0;

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
                try {
                    scrollToLastDynamicElement();
                } catch (Exception e) {
                    System.out.println("El elemento dinámico para el scroll no está disponible: " + e.getMessage());
                }
                scrollToBottom();
                // 1. Guardar la referencia del WebElement actual para la aserción de Staleness
                this.savedPreviousElement = driver.findElement(Btntarget);

                // 2. Guardar el texto actual para controlar el cambio dinámico entre clics
                String currentText = getText(Btntarget);

                // 3. Ejecutar el clic
                safeClick(Btnremplazar);

                // 4. Esperar hasta que el texto cambie en el DOM
                wait.until(driverInstance -> !getText(Btntarget).equals(currentText));
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

    public String getUpdatedNodeText(String expectedVersion) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(Btntarget, expectedVersion));
        return driver.findElement(Btntarget).getText();
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
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }

    public boolean isNodeRemoved() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // Espera a que la lista de elementos en el DOM que coinciden con el localizador sea exactamente 0
            return wait.until(ExpectedConditions.numberOfElementsToBe(Btntarget, 0)).isEmpty();
        } catch (TimeoutException e) {
            // Si pasan los 5 segundos y el elemento sigue presente en el DOM, retorna false
            return false;
        }
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