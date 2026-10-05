package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class elementPage extends BasePage {

    // Locadores
    private final By nativeCategorySelect = By.id("native-select");
    private final By customRegionDropdownButton = By.id("custom-select-trigger");
    private final By selectsStatusMessage = By.id("select-result");
    private final By addDynamicOptionsButton = By.id("add-choice");
    private final By dynamicStatusMessage = By.xpath("//*[contains(text(),'Controles dinámicos añadidos')]");
    // Locators para iFrame
    private final By iframeSubmitButton = By.xpath("//button[contains(normalize-space(),'Enviar desde iFrame')]");
    private final By iframeResult = By.id("iframe-result");
    // Locator del host del Shadow DOM
    private final By shadowHostLocator = By.id("shadow-component"); // Ajustar id/css selector según tu DOM
    // Locators para Modal
    private final By btnmodal = By.id("open-modal");
    private final By btnfondo = By.id("behind-modal");
    private final By btnGuaCer = By.id("save-modal");
    private final By modalHeader = By.id("demo-modal");
    private final By inputModal = By.id("modal-input");
    private final By txtResultadoGuardado = By.id("modal-result");
    /**
     * Método optimizado para manejar la acción de clic en diferentes botones
     * según su identificador o texto mediante un switch statement.
     */
    private void safeClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
    public void clickButton(String buttonName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement button;

        switch (buttonName.trim().toLowerCase()) {
            case "custom region dropdown":
                button = wait.until(ExpectedConditions.presenceOfElementLocated(customRegionDropdownButton));
                break;

            case "añadir opciones dinámicas":
                WebElement buttondi = wait.until(ExpectedConditions.presenceOfElementLocated(addDynamicOptionsButton));
                scrollToElement(buttondi);
                button = buttondi;
                break;
            case "enviar desde iframe":// 1. En minúsculas para coincidir con .toLowerCase()
                // 2. Cambiar el foco al iFrame (ajusta el selector si tienes el By del iframe guardado)
                WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
                driver.switchTo().frame(iframe);

                // 3. Buscar el botón dentro del iFrame
                button = wait.until(ExpectedConditions.presenceOfElementLocated(iframeSubmitButton));

                // 4. Hacer scroll y clic
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
                try {
                    button.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
                }

                // 5. Salir del iFrame y volver a la página principal
                driver.switchTo().defaultContent();
                return; // Usamos return para evitar que ejecute safeClick fuera del iframe
            case "validar código": // Texto en minúsculas debido a .toLowerCase()
                SearchContext shadowRoot = getShadowRoot();
                WebElement shadowButton = shadowRoot.findElement(By.cssSelector("#shadow-submit"));

                // Hacer scroll e interactuar directamente
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", shadowButton);
                try {
                    shadowButton.click();
                } catch (Exception e) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", shadowButton);
                }

                // Retornamos directamente para evitar llamar a safeClick(button)
                return;
            case "abrir modal":
                WebElement btnElement = wait.until(ExpectedConditions.presenceOfElementLocated(btnmodal));
                scrollToElement(btnElement);
                button = btnElement; // <-- Asignación corregida aquí
                break;
            case "guardar y cerrar":
                button = wait.until(ExpectedConditions.presenceOfElementLocated(btnGuaCer));
                break;
            case "acción de fondo":
                button = wait.until(ExpectedConditions.presenceOfElementLocated(btnfondo));
                break;

            default:
                By genericButton = By.xpath("//button[contains(normalize-space(), '" + buttonName + "')]");
                button = wait.until(ExpectedConditions.presenceOfElementLocated(genericButton));
                break;
        }
        // safeClick se ejecuta únicamente para los botones fuera de Shadow DOM
        safeClick(button);
    }

    public void selectNativeCategory(String categoryText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement selectElement = wait.until(ExpectedConditions.visibilityOfElementLocated(nativeCategorySelect));
        Select select = new Select(selectElement);
        select.selectByVisibleText(categoryText);
    }

    public String getSelectedNativeCategory() {
        WebElement selectElement = driver.findElement(nativeCategorySelect);
        Select select = new Select(selectElement);
        return select.getFirstSelectedOption().getText().trim();
    }

    public void selectCustomRegionOption(String regionName) {
        By dynamicOption = By.xpath("//*[contains(text(),'" + regionName + "')]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(dynamicOption)).click();
    }

    public String getCustomRegionButtonText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(customRegionDropdownButton));

        String fullText = button.getText().trim();
        return fullText.split("\n")[0].trim();
    }

    public String getStatusMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement statusElement = wait.until(ExpectedConditions.visibilityOfElementLocated(selectsStatusMessage));
        return statusElement.getText().trim();
    }

    public String getDynamicStatusMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dynamicStatusMessage)).getText().trim();
    }

    public void clickCheckboxByLabel(String labelText) {
        By checkboxLocator = By.xpath("//div[@id='choice-list']//label[contains(normalize-space(), '" + labelText + "')]/input[@type='checkbox']");
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(checkboxLocator));

        // 1. Centrar el elemento en la ventana para garantizar visibilidad
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", checkbox);

        // 2. Clic con fallback por JS si el label superpone al input
        try {
            checkbox.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }
    }

    public void clickRadioButtonByLabel(String labelText) {
        By radioLocator = By.xpath("//div[@id='choice-list']//label[contains(normalize-space(), '" + labelText + "')]/input[@type='radio']");
        WebElement radioButton = wait.until(ExpectedConditions.presenceOfElementLocated(radioLocator));

        // 1. Centrar el radio button en la ventana visible
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", radioButton);

        // 2. Intentar clic nativo y forzar por JS si el label lo intercepta
        try {
            radioButton.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
        }
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
    public void enterIframeName(String name) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Cambiar al iframe
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(iframe);

        // 2. Buscar el input y escribir el texto
        WebElement nameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input")));
        nameInput.clear();
        if (name != null && !name.trim().isEmpty()) {
            nameInput.sendKeys(name);
        }

        // 3. Volver al documento principal
        driver.switchTo().defaultContent();
    }

    /**
     * Obtiene el texto del saludo y regresa al contexto principal
     */
    public String getIframeGreetingText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // 1. Cambiar al iFrame
        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("iframe")));
        driver.switchTo().frame(iframe);

        String message = "";

        // 2. Comprobar si hay error nativo HTML5 (cuando el input está vacío)
        try {
            WebElement inputElement = driver.findElement(By.id("iframe-name"));
            String html5Error = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].validationMessage;", inputElement);

            if (html5Error != null && !html5Error.trim().isEmpty()) {
                message = html5Error.trim();
            }
        } catch (Exception e) {
            // Ignorar si no se encuentra
        }

        // 3. Si no hay error de validación HTML5, leemos el saludo de éxito (<p id="iframe-result">)
        if (message.isEmpty()) {
            try {
                WebElement responseElement = wait.until(ExpectedConditions.visibilityOfElementLocated(iframeResult));
                message = responseElement.getText().trim();
            } catch (Exception e) {
                message = "";
            }
        }

        // 4. Salir del iFrame obligatoriamente
        driver.switchTo().defaultContent();

        return message;
    }
    private SearchContext getShadowRoot() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 1. Espera a que el Shadow Host exista en el DOM principal
        WebElement shadowHost = wait.until(ExpectedConditions.presenceOfElementLocated(shadowHostLocator));

        // 2. Hace scroll suave/centrado hacia el elemento para que la pantalla baje
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", shadowHost);

        // 3. Devuelve el contexto del Shadow Root
        return shadowHost.getShadowRoot();
    }

    /**
     * Escribe el código dentro del input dentro del Shadow DOM
     */
    public void enterShadowCode(String code) {
        SearchContext shadowRoot = getShadowRoot();
        WebElement input = shadowRoot.findElement(By.cssSelector("#shadow-input"));
        input.clear();
        input.sendKeys(code);
    }

     /**
     * Obtiene el mensaje del resultado dentro del Shadow DOM
     */
     public String getShadowResultMessage() {
         SearchContext shadowRoot = getShadowRoot();
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

         // Cambia By.id("shadow-result") por By.cssSelector("#shadow-result")
         WebElement resultElement = wait.until(d -> shadowRoot.findElement(By.cssSelector("#shadow-result")));

         return resultElement.getText();
     }
    public boolean isModalDisplayed(String expectedTitle) {
        try {
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(modalHeader));
            return header.getText().contains(expectedTitle);
        } catch (Exception e) {
            return false;
        }
    }

    public void enterTextInModal(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(inputModal));
        input.clear();
        if (text != null && !text.isEmpty()) {
            input.sendKeys(text);
        }
    }

    public boolean isModalClosed() {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(modalHeader));
    }

    public String getSavedResultMessage() {
        WebElement btnElement = wait.until(ExpectedConditions.presenceOfElementLocated(txtResultadoGuardado));
        scrollToElement(btnElement);
        WebElement resultElement = wait.until(ExpectedConditions.visibilityOfElementLocated(txtResultadoGuardado));
        return resultElement.getText().trim();
    }
    public String getBackgroundActionResultMessage() {
        WebElement btnElement = wait.until(ExpectedConditions.presenceOfElementLocated(txtResultadoGuardado));
        scrollToElement(btnElement);
        try {
            WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(txtResultadoGuardado));
            return result.getText().trim();
        } catch (Exception e) {
            return "";
        }
    }


}