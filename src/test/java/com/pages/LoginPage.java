package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

public class LoginPage extends BasePage {

  // =========================================================================
  // LOCATORS
  // =========================================================================
  private final By fullNameInput = By.id("userName");
  private final By emailInput = By.id("userEmail");
  private final By currentAddressInput = By.id("currentAddress");
  private final By permanentAddressInput = By.id("permanentAddress");
  private final By submitBtn = By.id("submit");
  private final By outputBox = By.id("output");
  private final By userNumberInput = By.id("userNumber");
  private final By subjectsInput = By.id("subjectsInput");
  private final By modalTitle = By.id("example-modal-sizes-title-lg");
  private final By newTabButton = By.id("tabButton");
  private final By newWindowButton = By.id("windowButton");
  private final By newWindowMessageButton = By.id("messageWindowButton");
  private final By samplePageHeader = By.id("sampleHeading");


  // Elements - Checkbox / Tree
  private final By expandAllBtn = By.xpath("//span[contains(@class,'rc-tree-switcher')]");

  // Elements - Web Tables
  private final By addBtn = By.id("addNewRecordButton");
  private final By firstNameInput = By.id("firstName");
  private final By lastNameInput = By.id("lastName");
  private final By userEmailInput = By.id("userEmail");
  private final By ageInput = By.id("age");
  private final By salaryInput = By.id("salary");
  private final By departmentInput = By.id("department");
  private final By searchBox = By.id("searchBox");

  // Elements - Buttons
  private final By doubleClickBtn = By.id("doubleClickBtn");
  private final By rightClickBtn = By.id("rightClickBtn");

  // Upload & Download
  private final By downloadBtn = By.id("downloadButton");
  private final By uploadFileInput = By.id("uploadFile");
  private final By uploadedFilePathText = By.id("uploadedFilePath");

  // Dynamic Properties
  private final By enableAfterBtn = By.id("enableAfter");
  private final By colorChangeBtn = By.id("colorChange");
  private final By visibleAfterBtn = By.id("visibleAfter");

  // Images
  private final By validImg = By.xpath("//p[text()='Valid image']/following-sibling::img[1]");
  private final By brokenImg = By.xpath("//p[text()='Broken image']/following-sibling::img[1]");
  // Locadores para la sección Alerts
  private final By alertButton = By.id("alertButton");
  private final By timerAlertButton = By.id("timerAlertButton");
  private final By confirmButton = By.id("confirmButton");
  private final By promtButton = By.id("promtButton");
  private final By confirmResult = By.id("confirmResult");
  private final By promptResult = By.id("promptResult");
  // Locator para el texto dentro de los iframes
  private final By iframeHeading = By.id("sampleHeading");
  // Locators para Nested Frames
  private final By parentFrame = By.id("frame1");
  private final By parentBody = By.tagName("body");
  private final By childIframe = By.cssSelector("iframe[srcdoc*='Child Iframe']");
  private final By childBody = By.tagName("p");

  private final By modalTit = By.cssSelector(".modal-title");
  private final By closeModalButton = By.cssSelector(".modal-footer button, #closeModalSmall, #closeModalLarge");
  private final By modalContainer = By.className("modal-content");
  // Locators para Auto Complete
  private final By multipleAutoCompleteInput = By.id("autoCompleteMultipleInput");
  private final By singleAutoCompleteInput = By.id("autoCompleteSingleInput");
  // Locators para Date Picker
  private final By selectDateInput = By.id("datePickerMonthYearInput");
  private final By dateAndTimeInput = By.id("dateAndTimePickerInput");
  // Locators para Slider
  private final By sliderInput = By.xpath("//input[contains(@class,'range-slider')]");
  private final By sliderValueInput = By.id("sliderValue");
  // Locators para Progress Bar
  private final By startStopButton = By.id("startStopButton");
  private final By progressBar = By.xpath("//div[@role='progressbar']");
  private final By resetButton = By.id("resetButton");

  // Locators para Tool Tips
  private final By toolTipButton = By.id("toolTipButton");
  private final By toolTipTextField = By.cssSelector("#toolTipTextField"); // Corregido: apunta al <input> real
  private final By contraryLink = By.xpath("//a[text()='Contrary']");
  private final By sectionLink = By.xpath("//a[text()='1.10.32']");
  private final By tooltipContainer = By.xpath("//div[@class='tooltip-inner'] | //div[@role='tooltip']//div[@class='tooltip-inner']");
  // Locador para Standard Multi Select
  private final By standardMultiSelect = By.id("cars");
  private final By selectValueInput = By.xpath("(//div[@id='selectMenuContainer']//div[contains(@class,'indicatorContainer')])[1]");
  private final By selectOneInput = By.id("selectOne");
  private final By oldStyleSelect = By.id("oldSelectMenu");
  private final By multiSelectInput = By.xpath("(//div[@id='selectMenuContainer']//div[contains(@class,'indicatorContainer')])[3]");
  // =========================================================================
  // ACTIONS & METHODS
  // =========================================================================

  public void navigateToMainPage() {
    navigateTo("https://demoqa.com/");
  }
  public void selectCategoryCard(String categoryName) {
    By categoryCard = By.xpath("//h5[text()='" + categoryName + "']/ancestor::div[contains(@class,'card-body')]");
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

  public void clickMenuItems(String optionName) {
    By menuItem = By.xpath("//a[contains(@class,'router-link') and normalize-space()='" + optionName + "']");
    try {
      // Intenta usar tu metodo click() normal de BasePage
      click(menuItem);
    } catch (ElementClickInterceptedException e) {
      // Solo si la opción particular está tapada (ej. por el footer), la desplaza y da clic
      WebElement element = driver.findElement(menuItem);
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
      js.executeScript("arguments[0].click();", element);
    }
  }

  // --- Text Box ---
  public void enterFullName(String name) { type(fullNameInput, name); }
  public void enterEmail(String email) { type(emailInput, email); }
  public void enterCurrentAddress(String addr) { type(currentAddressInput, addr); }
  public void enterPermanentAddress(String addr) { type(permanentAddressInput, addr); }
  public void clickSubmit() { click(submitBtn); }
  public boolean isOutputDisplayed() { return isDisplayed(outputBox); }

  // --- Checkbox & Tree ---
  public void expandAllTreeFolders() { click(expandAllBtn); }

  public void expandFolders(String folderName) {
    By folderToggle = By.xpath("//span[contains(@class,'rc-tree-switcher')][following-sibling::span[contains(@class,'rc-tree-checkbox') and @aria-label='Select " + folderName + "']]");
    if (isDisplayed(folderToggle)) {
      click(folderToggle);
    }
  }

  public void selectCheckboxByName(String checkboxName) {
    By checkbox = By.xpath("//span[contains(@class,'rc-tree-checkbox') and @aria-label='Select " + checkboxName + "']");
    click(checkbox);
  }

  public String getResultText() {
    if (!driver.findElements(By.className("text-success")).isEmpty()) {
      return driver.findElement(By.className("text-success")).getText();
    }
    // Si no existe, busca el contenedor id="result" de Check Box
    else {
      return driver.findElement(By.id("result")).getText();
    }
  }

  // --- Radio Button ---
  public void selectRadioButtonByName(String optionName) {
    By radioLabel = By.xpath("//label[contains(@class,'form-check-label') and text()='" + optionName +"']");
    click(radioLabel);
  }

  // --- Web Tables ---
  public void clickAddButton() { click(addBtn); }

  public void fillRegistrationForm(String firstName, String lastName, String email, String age, String salary, String department) {
    type(firstNameInput, firstName);
    type(lastNameInput, lastName);
    type(userEmailInput, email);
    type(ageInput, age);
    type(salaryInput, salary);
    type(departmentInput, department);
  }

  public void clickSubmitButton() { click(submitBtn); }

  public void searchInTable(String keyword) { type(searchBox, keyword); }

  public boolean isUserPresentInTable(String keyword) {
    By cellLocator = By.xpath("//tbody//td[contains(text(), '" + keyword + "')]");
    return isDisplayed(cellLocator);
  }

  // --- Buttons ---
  public void performDoubleClick() { doubleClick(doubleClickBtn); }
  public void performRightClick() { rightClick(rightClickBtn); }

  public void performDynamicClick(String buttonText) {
    By dynamicBtn = By.xpath("//button[text()='" + buttonText + "']");
    click(dynamicBtn);
  }

  public boolean isMessageDispalay(String message) {
    By msgLocator = By.xpath("//*[contains(text(), '" + message + "')]");
    return isDisplayed(msgLocator);
  }

  // --- Links & Windows ---
  public void clickLinkByText(String linkText) {
    // Busca cualquier enlace <a> cuyo texto visible o ID coincida
    String xpath;

    // Si pasas "Home", podemos asegurarnos de hacer click específicamente por su id para evitar ambigüedades:
    if (linkText.equalsIgnoreCase("Home")) {
      xpath = "//a[@id='simpleLink']";
    } else if (linkText.toLowerCase().contains("home")) {
      xpath = "//a[@id='dynamicLink']";
    } else {
      xpath = "//a[normalize-space()='" + linkText + "' or @id='" + linkText.toLowerCase() + "']";
    }
    WebElement link = driver.findElement(By.xpath(xpath));

    // Scroll centrado y clic inmediato con JavaScript
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
    js.executeScript("arguments[0].click();", link);
  }

  public boolean isNewTabOpenedWithUrl(String expectedUrl) {
    String originalWindow = driver.getWindowHandle();
    for (String windowHandle : driver.getWindowHandles()) {
      if (!originalWindow.equals(windowHandle)) {
        driver.switchTo().window(windowHandle);
        break;
      }
    }
    return wait.until(ExpectedConditions.urlContains(expectedUrl));
  }

  public boolean isApiResponseDisplayed(String statusCode, String statusText) {
    By responseMsg = By.id("linkResponse");
    String text = getText(responseMsg);
    return text.contains(statusCode) && text.contains(statusText);
  }

  public boolean isStatusCodeErrorDisplayed(String expectedCode) {
    By responseMsg = By.xpath("//*[contains(text(), 'This page returned a " + expectedCode + " status code')]");
    return getText(responseMsg).contains(expectedCode);
  }

  // --- Images ---
  public boolean isValidImageDisplayed() {
    WebElement img = wait.until(ExpectedConditions.visibilityOfElementLocated(validImg));
  // en este caso no hay la imagine por eso tenemos que escribir condition falsa tine que ser > 0 escribimos == 0
    return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].naturalWidth == 0;", img);
  }

  public boolean isBrokenImageDisplayed() {
    WebElement img = driver.findElement(brokenImg);
    return (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].naturalWidth == 0;", img);
  }

  // --- Upload & Download ---
  public void clickDownloadButton() { click(downloadBtn); }

  public boolean isFileDownloaded(String fileName) {
    String downloadPath = System.getProperty("user.home") + "/Downloads/" + fileName;
    File file = new File(downloadPath);
    return file.exists();
  }

  public void uploadFile(String absoluteFilePath) {
    driver.findElement(uploadFileInput).sendKeys(absoluteFilePath);
  }

  public String getUploadedFilePathText() {
    return getText(uploadedFilePathText);
  }

  // --- Dynamic Properties ---
  public boolean isButtonEnabledAfterWait(int seconds) {
    WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    return customWait.until(ExpectedConditions.elementToBeClickable(enableAfterBtn)).isEnabled();
  }

  public boolean isColorChanged() {
    WebElement button = driver.findElement(colorChangeBtn);
    return wait.until(d -> button.getAttribute("class").contains("text-danger") ||
            button.getCssValue("color").contains("220, 53, 69"));
  }

  public boolean isButtonVisibleAfterWait(int seconds) {
    WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    return customWait.until(ExpectedConditions.visibilityOfElementLocated(visibleAfterBtn)).isDisplayed();
  }
  private void selectGender(String gender) {
    By genderRadio = By.xpath("//label[contains(text(),'" + gender + "')]");
    WebElement element = driver.findElement(genderRadio);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

  private void selectHobby(String hobby) {
    By hobbyCheckbox = By.xpath("//label[contains(text(),'" + hobby + "')]");
    WebElement element = driver.findElement(hobbyCheckbox);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }
  public void fillForm(Map<String, String> data) {
    type(firstNameInput, data.get("firstName"));
    type(lastNameInput, data.get("lastName"));
    type(userEmailInput, data.get("email"));

    if (data.containsKey("gender")) {
      selectGender(data.get("gender"));
    }

    type(userNumberInput, data.get("mobile"));

    if (data.containsKey("subject")) {
      WebElement subjectEl = wait.until(d -> d.findElement(subjectsInput));
      subjectEl.sendKeys(data.get("subject"));
      subjectEl.sendKeys(Keys.ENTER);
    }

    if (data.containsKey("hobby")) {
      selectHobby(data.get("hobby"));
    }
  }
  public String getModalTitleText() {
    return getText(modalTitle);
  }
  public void clickButtonByName(String buttonName) {
    switch (buttonName) {
      case "New Tab":
        click(newTabButton);
        break;
      case "New Window":
        click(newWindowButton);
        break;
      case "New Window Message":
        click(newWindowMessageButton);
        break;
      default:
        throw new IllegalArgumentException("Botón no reconocido: " + buttonName);
    }
  }
  public String getHeadingFromNewWindow() {
    String originalWindow = driver.getWindowHandle();

    // Obtener todos los identificadores de ventana actualmente abiertos
    Set<String> allWindows = driver.getWindowHandles();

    for (String windowHandle : allWindows) {
      if (!windowHandle.equals(originalWindow)) {
        driver.switchTo().window(windowHandle);
        break;
      }
    }

    String text = "";

    try {
      // Pausa breve para dar tiempo al navegador a inyectar el texto
      Thread.sleep(10);
      // Para New Tab y New Window estándar
      if (driver.findElements(By.id("sampleHeading")).size() > 0) {
        text = driver.findElement(By.id("sampleHeading")).getText();
      } else {
        // Para New Window Message (about:blank)
        // Extraemos directamente el texto interno del DOM
        JavascriptExecutor js = (JavascriptExecutor) driver;
        text = (String) js.executeScript("return document.body.innerText;");
      }
    } catch (Exception e) {
      text = driver.findElement(By.tagName("body")).getText();
    } finally {
      // Cerrar la ventana de mensaje emergente y regresar
      driver.close();
      driver.switchTo().window(originalWindow);
    }

    return text.trim();
  }
  public void clickAlertButton(String alertType) {
    switch (alertType.toLowerCase()) {
      case "simple":
        click(alertButton);
        break;
      case "timer":
        click(timerAlertButton);
        break;
      case "confirm":
        click(confirmButton);
        break;
      case "prompt":
        click(promtButton);
        break;
      default:
        throw new IllegalArgumentException("Tipo de alerta no soportado: " + alertType);
    }
  }
  // Método para interactuar con la alerta (escribir o aceptar/cancelar)
  public String interactWithAlert(String alertType, String inputText) {
    Alert alert = waitForAlert(); // Espera explícita
    String alertText = alert.getText(); // Captura el texto de la alerta emergente

    if (alertType.equalsIgnoreCase("prompt") && !inputText.equals("N/A")) {
      alert.sendKeys(inputText);
      alert.accept();
      return getText(promptResult); // Devuelve texto en HTML de la página
    } else if (alertType.equalsIgnoreCase("confirm")) {
      alert.dismiss();
      return getText(confirmResult); // Devuelve texto en HTML de la página
    } else {
      alert.accept();
      return alertText; // Devuelve el texto leído del pop-up (simple o timer)
    }
  }

  // Método para obtener el mensaje (sea dentro de la alerta o de la página)
  public String getAlertOrPageResultText(String alertType) {
    if (alertType.equalsIgnoreCase("confirm")) {
      return getText(confirmResult);
    } else if (alertType.equalsIgnoreCase("prompt")) {
      return getText(promptResult);
    } else {
      // En alertas simples el texto ya fue leído o aceptado
      return "";
    }
  }
  public void switchToIframeById(String id) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(id));
  }
  public String getIframeHeadingText() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    return wait.until(ExpectedConditions.visibilityOfElementLocated(iframeHeading)).getText();
  }
  public void switchToDefaultContent() {
    driver.switchTo().defaultContent();
  }
  public void switchToParentFrame() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(parentFrame));
  }
  public String getParentFrameText() {
    return driver.findElement(parentBody).getText().trim();
  }
  public void switchToChildFrame() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(childIframe));
  }
  // Obtener texto del frame Hijo
  public String getChildFrameText() {
    return driver.findElement(childBody).getText().trim();
  }
  public By modalButton(String modalType){
    return By.xpath("//button[text()='" + modalType + "']");
  }
  public void clickModalButton(String modalType) {
    click(modalButton(modalType));
  }
  public String getModalTitText() {
    return getText(modalTit);
  }
  public void closeModal(){
    click(closeModalButton);
  }
  public boolean isModalClosed() {
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(modalContainer));
  }
  private By accordianHeader(String headingText) {
    return By.xpath("//button[contains(@class,'accordion-button') and normalize-space()='" + headingText + "']");
  }

  // Locator para el contenedor desplegable con el texto/contenido
  private By accordianContent(String headingText) {
    return By.xpath("//h2[contains(@class,'accordion-header')][.//button[normalize-space()='" + headingText + "']]/following-sibling::div[contains(@class,'accordion-collapse')]");
  }

  // Métodos de acción
  public void clickAccordianSection(String headingText) {
    By contentLocator = accordianContent(headingText);

    // Verifica si la sección ya está visible (contiene la clase 'show')
    boolean isExpanded = driver.findElement(contentLocator).getAttribute("class").contains("show");

    // Solo hace clic si la sección está cerrada
    if (!isExpanded) {
      click(accordianHeader(headingText));
    }
  }

  public boolean isAccordianContentVisible(String headingText) {
    return wait.until(ExpectedConditions.attributeContains(accordianContent(headingText), "class", "show"));
  }

  public String getAccordianContentText(String headingText) {
    return getText(accordianContent(headingText));
  }
  private By autoCompleteOption(String optionText) {
    return By.xpath("//div[contains(@class,'auto-complete__option') and text()='" + optionText + "']");
  }

  private By multipleFieldValue(String colorName) {
    return By.xpath("//div[@id='autoCompleteMultipleContainer']//div[contains(@class,'auto-complete__multi-value__label') and text()='" + colorName + "']");
  }

  private final By singleFieldValue = By.cssSelector("#autoCompleteSingleContainer .auto-complete__single-value");

  // Métodos de acción
  public void typeInAutoCompleteField(String fieldType, String text) {
    By inputLocator = fieldType.equalsIgnoreCase("Multiple") ? multipleAutoCompleteInput : singleAutoCompleteInput;
    WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
    inputElement.sendKeys(text);
  }

  public void selectAutoCompleteOption(String optionText) {
    click(autoCompleteOption(optionText));
  }

  public boolean isColorDisplayedInField(String fieldType, String colorName) {
    if (fieldType.equalsIgnoreCase("Multiple")) {
      return wait.until(ExpectedConditions.visibilityOfElementLocated(multipleFieldValue(colorName))).isDisplayed();
    } else {
      String actualValue = getText(singleFieldValue);
      return actualValue.equalsIgnoreCase(colorName);
    }
  }
  // Métodos de acción
  public void inputDatePickerValue(String fieldType, String value) {
    By locator = fieldType.equalsIgnoreCase("Select Date") ? selectDateInput : dateAndTimeInput;
    WebElement inputElement = wait.until(ExpectedConditions.elementToBeClickable(locator));

    // Limpiar el campo seleccionando todo el texto para evitar concatenaciones
    inputElement.sendKeys(Keys.CONTROL + "a");
    inputElement.sendKeys(Keys.BACK_SPACE);
    inputElement.sendKeys(value);
    inputElement.sendKeys(Keys.ENTER);
  }

  public String getDatePickerValue(String fieldType) {
    By locator = fieldType.equalsIgnoreCase("Select Date") ? selectDateInput : dateAndTimeInput;
    WebElement inputElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return inputElement.getAttribute("value");
  }
  // Métodos de acción
  public void setSliderValue(String targetValue) {
    WebElement slider = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderInput));

    // Disparar el evento de cambio nativo de React para sliders
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(
            "var slider = arguments[0];" +
                    "var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
                    "nativeInputValueSetter.call(slider, arguments[1]);" +
                    "slider.dispatchEvent(new Event('input', { bubbles: true }));" +
                    "slider.dispatchEvent(new Event('change', { bubbles: true }));",
            slider, targetValue
    );
  }

  public String getSliderValue() {
    WebElement valueElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderValueInput));
    return valueElement.getAttribute("value");
  }
  // Métodos de acción
  public void clickStartProgressBar() {
    click(startStopButton);
  }

  public boolean waitForProgressBarValue(String expectedPercentage) {
    // Espera de hasta 20 segundos para que la barra alcance el porcentaje indicado
    WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
    return longWait.until(ExpectedConditions.attributeToBe(progressBar, "aria-valuenow", expectedPercentage.replace("%", "")));
  }

  public boolean isResetButtonVisible() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(resetButton)).isDisplayed();
  }
  private By tabNavigationLink(String tabName) {
    return By.xpath("//button[@role='tab' and normalize-space()='" + tabName + "']");
  }

  private By tabContentPanel(String tabName) {
    String tabId = tabName.toLowerCase();
    return By.id("demo-tabpane-" + tabId);
  }

  // Métodos de acción
  public void clickTab(String tabName) {
    click(tabNavigationLink(tabName));
  }

  public boolean isTabActive(String tabName) {
    WebElement tabElement = wait.until(ExpectedConditions.visibilityOfElementLocated(tabNavigationLink(tabName)));
    return "true".equalsIgnoreCase(tabElement.getAttribute("aria-selected"));
  }

  public String getTabContentText(String tabName) {
    WebElement panelElement = wait.until(ExpectedConditions.visibilityOfElementLocated(tabContentPanel(tabName)));
    return panelElement.getText();
  }
  public void hoverOverElement(String elementName) {
    By locator;
    switch (elementName.toLowerCase()) {
      case "button":
        locator = toolTipButton;
        break;
      case "field":
        locator = toolTipTextField;
        break;
      case "contrary":
        locator = contraryLink;
        break;
      case "1.10.32":
        locator = sectionLink;
        break;
      default:
        throw new IllegalArgumentException("Elemento no válido: " + elementName);
    }
    WebElement element = waitForVisibility(locator);
    hoverOver(element);
    waitForVisibility(tooltipContainer);
  }

  public String getTooltipText() {
    WebElement tooltip = wait.until(ExpectedConditions.visibilityOfElementLocated(tooltipContainer));
    return tooltip.getText();
  }

  // --- Menu Multinivel ---
  public void hoverOverMenuItem(String itemText) {
    String cleanText = itemText.replace("»", "").trim();
    By itemLocator = By.xpath("//a[contains(normalize-space(.), '" + cleanText + "')]");

    WebElement itemElement = wait.until(ExpectedConditions.presenceOfElementLocated(itemLocator));
    scrollToElement(itemElement);

    // Disparar evento de mouseover mediante JavaScript para asegurar que el submenú se abra
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(
            "var event = document.createEvent('MouseEvents');" +
                    "event.initMouseEvent('mouseover', true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);" +
                    "arguments[0].dispatchEvent(event);",
            itemElement
    );

  }

  public void clickSubSubMenuItem(String subSubItemText) {
    String cleanText = subSubItemText.replace("»", "").trim();
    By itemLocator = By.xpath("//a[contains(normalize-space(.), '" + cleanText + "')]");

    // 1. Esperamos a que el elemento esté presente en el DOM
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(itemLocator));

    // 2. Ejecutamos el clic mediante JavaScript para omitir restricciones de visibilidad/hover de Selenium
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", element);

  }
  public void selectOptionInSelectMenu(String dropdownName, String optionValue) throws InterruptedException {
    switch (dropdownName) {
      case "Select Value":
        scrollToTop();
        Thread.sleep(2000);
        scrollToTop();
        click(selectValueInput);
        click(By.xpath("//div[contains(@id,'react-select') and normalize-space(.)='" + optionValue + "']"));
        break;

      case "Select One":
        click(selectOneInput);
        click(By.xpath("//div[contains(@id,'react-select') and normalize-space(.)='" + optionValue + "']"));
        break;

      case "Old Style Select Menu":
        org.openqa.selenium.support.ui.Select oldSelect =
                new org.openqa.selenium.support.ui.Select(waitForVisibility(oldStyleSelect));
        oldSelect.selectByVisibleText(optionValue);
        break;

      case "Multiselect drop down":
        WebElement multiInput = waitForVisibility(multiSelectInput);
        scrollToElement(multiInput);
        multiInput.click();
        click(By.xpath("//div[contains(@id,'react-select') and text()='" + optionValue + "']"));
        break;

      case "Standard multi select":
        WebElement carsElement = waitForVisibility(standardMultiSelect);
        scrollToElement(carsElement);
        org.openqa.selenium.support.ui.Select multiCarsSelect =
                new org.openqa.selenium.support.ui.Select(carsElement);
        multiCarsSelect.selectByVisibleText(optionValue);
        break;

      default:
        throw new IllegalArgumentException("Menú desplegable no encontrado: " + dropdownName);
    }
  }

  public boolean isStandardOptionSelected(String expectedOption) {
    org.openqa.selenium.support.ui.Select multiCarsSelect =
            new org.openqa.selenium.support.ui.Select(driver.findElement(standardMultiSelect));
    return multiCarsSelect.getAllSelectedOptions()
            .stream()
            .anyMatch(opt -> opt.getText().equals(expectedOption));
  }



}