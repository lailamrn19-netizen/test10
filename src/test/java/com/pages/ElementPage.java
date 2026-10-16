package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static com.pages.Locators.*;

import java.time.Duration;

public class ElementPage extends BasePage {

    /**
     * Método optimizado para manejar la acción de clic en diferentes botones
     * según su identificador o texto mediante un switch statement.
     */
    // ==========================================
    // MÉTODOS DE ACCIÓN / CLICS
    // ==========================================

    public void clickButton(String buttonName) {
        switch (buttonName.trim().toLowerCase()) {
            case "custom region dropdown":
                safeClick(customRegionDropdownButton);
                break;

            case "añadir opciones dinámicas":
                safeClick(addDynamicOptionsButton);
                break;

            case "enviar desde iframe":
                scrollToBottom();
                executeInsideIframe(() -> safeClick(iframeSubmitButton));
                break;

            case "validar código":
                clickShadowButton("#shadow-submit");
                break;

            case "abrir modal":
                safeClick(btnModal);
                break;

            case "guardar y cerrar":
                safeClick(btnGuardarCerrar);
                break;

            case "acción de fondo":
                safeClick(btnFondo);
                break;

            default:
                By genericButton = By.xpath("//button[contains(normalize-space(), '" + buttonName + "')]");
                safeClick(genericButton);
                break;
        }
    }

    // ==========================================
    // SELECTS & DROPDOWNS
    // ==========================================
    public void selectNativeCategory(String categoryText) {
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nativeCategorySelect));
        new Select(selectElement).selectByVisibleText(categoryText);
    }

    public String getSelectedNativeCategory() {
        WebElement selectElement = driver.findElement(nativeCategorySelect);
        return new Select(selectElement).getFirstSelectedOption().getText().trim();
    }

    public void selectCustomRegionOption(String regionName) {
        By dynamicOption = By.xpath("//*[contains(text(),'" + regionName + "')]");
        safeClick(dynamicOption);
    }

    public String getCustomRegionButtonText() {
        String fullText = getText(customRegionDropdownButton);
        return fullText.split("\n")[0].trim();
    }

    public String getStatusMessageText() {
        return getText(selectsStatusMessage);
    }

    public String getDynamicStatusMessage() {
        return getText(dynamicStatusMessage);
    }

    // ==========================================
    // CHECKBOXES & RADIO BUTTONS
    // ==========================================

    public void clickCheckboxByLabel(String labelText) {
        By checkboxLocator = By.xpath("//div[@id='choice-list']//label[contains(normalize-space(), '" + labelText + "')]/input[@type='checkbox']");
        safeClick(checkboxLocator);
    }

    public void clickRadioButtonByLabel(String labelText) {
        By radioLocator = By.xpath("//div[@id='choice-list']//label[contains(normalize-space(), '" + labelText + "')]/input[@type='radio']");
        safeClick(radioLocator);
    }

    public boolean isCheckboxSelected(String labelText) {
        By checkbox = By.xpath("//label[contains(.,'" + labelText + "')]/input[@type='checkbox']");
        scrollToBottom();
        return driver.findElement(checkbox).isSelected();
    }

    public boolean isRadioButtonSelected(String labelText) {
        By radio = By.xpath("//label[contains(.,'" + labelText + "')]/input[@type='radio']");
        return driver.findElement(radio).isSelected();
    }

    // ==========================================
    // SECCIÓN IFRAME
    // ==========================================

    private void executeInsideIframe(Runnable action) {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
        driver.switchTo().frame(iframe);
        try {
            action.run();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    public void enterIframeName(String name) {
        scrollToBottom();
        executeInsideIframe(() -> type(iframeInput, name));
    }

    public String getIframeGreetingText() {
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(iframeLocator));
        driver.switchTo().frame(iframe);
        String message = "";

        try {
            String html5Error = getHtml5ValidationMessage(iframeNameInput);
            if (!html5Error.isEmpty()) {
                message = html5Error;
            } else {
                message = getText(iframeResult);
            }
        } catch (Exception e) {
            message = "";
        } finally {
            driver.switchTo().defaultContent();
        }

        return message;
    }

    // ==========================================
    // SECCIÓN SHADOW DOM
    // ==========================================

    private SearchContext getShadowRoot() {
        WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(shadowHostLocator));
        scrollToElement(shadowHost);
        return shadowHost.getShadowRoot();
    }

    private void clickShadowButton(String cssSelector) {
        SearchContext shadowRoot = getShadowRoot();
        WebElement button = shadowRoot.findElement(By.cssSelector(cssSelector));
        safeClick(button);
    }

    public void enterShadowCode(String code) {
        SearchContext shadowRoot = getShadowRoot();
        WebElement input = shadowRoot.findElement(By.cssSelector("#shadow-input"));
        input.clear();
        if (code != null) {
            input.sendKeys(code);
        }
    }

    public String getShadowResultMessage() {
        SearchContext shadowRoot = getShadowRoot();
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement resultElement = customWait.until(d -> shadowRoot.findElement(By.cssSelector("#shadow-result")));
        return resultElement.getText().trim();
    }

    // ==========================================
    // SECCIÓN MODALES
    // ==========================================

    public boolean isModalDisplayed(String expectedTitle) {
        try {
            return getText(modalHeader).contains(expectedTitle);
        } catch (Exception e) {
            return false;
        }
    }

    public void enterTextInModal(String text) {
        type(inputModal, text);
    }

    public boolean isModalClosed() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(modalHeader));
    }

    public String getSavedResultMessage() {
        return getText(txtResultadoGuardado);
    }

    public String getBackgroundActionResultMessage() {
        try {
            return getText(txtResultadoGuardado);
        } catch (Exception e) {
            return "";
        }
    }
}