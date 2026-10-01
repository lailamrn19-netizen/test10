package com.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.time.Duration;
import java.util.List;
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
  //
  private By newUserBtn = By.id("newUser");
  private By registerBtn = By.id("register");
  private By firstNameInpu = By.id("firstname");
  private By lastNameInpu = By.id("lastname");
  private By userNameInput = By.id("userName");
  private By passwordInput = By.id("password");
  private By mainHeader = By.className("text-center");
  private By loginBtn = By.id("login");
  private By gotologinBtn = By.id("gotologin");
  private By gotoStore = By.id("gotoStore");
  private By userNameValueLabel = By.id("userName-value");
  private By logoutBtn = By.id("submit");

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
  private final By BtnBack = By.xpath("//button[text()='Back To Book Store']");
  private final By BtnColl = By.xpath("//button[text()='Add To Your Collection']");

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
  // Locadores para la caja restringida y su handle
  private By resizableBoxWithRestriction = By.id("resizableBoxWithRestriction");
  private By resizableBoxHandleWithRestriction = By.xpath("//div[@id='resizableBoxWithRestriction']/span[contains(@class,'react-resizable-handle')]");

  // Locadores para la caja libre (sin restricción) y su handle
  private By resizableBox = By.id("resizable");
  private By resizableBoxHandle = By.xpath("//div[@id='resizable']/span[contains(@class,'react-resizable-handle')]");
  // Variables para almacenar las posiciones iniciales
  private Point initialPosition;
  // Variables para almacenar las posiciones inicial y final
  private Point simpleInitialPosition;
  // Variable para almacenar la posición inicial antes de arrastrar
  private Point axisInitialPosition;
  private Point initialOnlyXPosition;
  private Point initialOnlyYPosition;
  // Variable de clase para almacenar la posición inicial antes del arrastre
  private Point cursorInitialPosition;
  // Locadores para la vista de Book Store
  private By booksTableRows = By.cssSelector("table tbody tr");
  private By bookTitleLinks = By.cssSelector(".rt-td a");

  // Locadores de las opciones del menú lateral
  private By bookStoreMenuOption = By.xpath("//span[text()='Book Store']");
  private By profileMenuOption = By.xpath("//span[text()='Profile']");
  private By bookStoreApiMenuOption = By.xpath("//span[text()='Book Store API']");
  private By notLoggedInLabel = By.id("notLoggin-label");
  private By profileRows = By.cssSelector("table tbody tr");
  // =========================================================================
  // ACTIONS & METHODS
  // =========================================================================

  public void navigateToMainPage(String pageName) {
    if (pageName.equalsIgnoreCase("demoqa")) {
      navigateTo("https://demoqa.com/");
    } else {
      navigateTo("http://localhost:3000/");
    }

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
    scrollToTop();
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
  public void enterFullName(String name) {
    type(fullNameInput, name);
  }

  public void enterEmail(String email) {
    type(emailInput, email);
  }

  public void enterCurrentAddress(String addr) {
    type(currentAddressInput, addr);
  }

  public void enterPermanentAddress(String addr) {
    type(permanentAddressInput, addr);
  }

  public void clickSubmit() {
    click(submitBtn);
  }

  public boolean isOutputDisplayed() {
    return isDisplayed(outputBox);
  }

  // --- Checkbox & Tree ---
  public void expandAllTreeFolders() {
    click(expandAllBtn);
  }

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
    By radioLabel = By.xpath("//label[contains(@class,'form-check-label') and text()='" + optionName + "']");
    click(radioLabel);
  }

  // --- Web Tables ---
  public void clickAddButton() {
    click(addBtn);
  }

  public void fillRegistrationForm(String firstName, String lastName, String email, String age, String salary, String department) {
    type(firstNameInput, firstName);
    type(lastNameInput, lastName);
    type(userEmailInput, email);
    type(ageInput, age);
    type(salaryInput, salary);
    type(departmentInput, department);
  }

  public void clickSubmitButton() {
    click(submitBtn);
  }

  public void searchInTable(String keyword) {
    type(searchBox, keyword);
  }

  public boolean isUserPresentInTable(String keyword) {
    By cellLocator = By.xpath("//tbody//td[contains(text(), '" + keyword + "')]");
    return isDisplayed(cellLocator);
  }

  // --- Buttons ---
  public void performDoubleClick() {
    doubleClick(doubleClickBtn);
  }

  public void performRightClick() {
    rightClick(rightClickBtn);
  }

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
  public void clickDownloadButton() {
    click(downloadBtn);
  }

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
      case "New User":
        click(newUserBtn);
        break;
      case "Register":
        click(registerBtn);
        break;
      case "Login":
        click(loginBtn);
        break;
      case "Back to Login":

        WebElement backBtn = waitForVisibility(gotologinBtn);
        // Hace scroll centrado para despejarlo del footer
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", backBtn);
        try {
          backBtn.click();
        } catch (ElementClickInterceptedException e) {
          // Clic forzado por JavaScript si el footer sigue bloqueando
          ((JavascriptExecutor) driver).executeScript("arguments[0].click();", backBtn);
        }
        break;
      case "Go To Book Store":
        click(gotoStore);
        break;
      case "Back To Book Store":
        click(BtnBack);
        break;
      case "Add To Your Collection":

        WebElement addBtn = wait.until(ExpectedConditions.presenceOfElementLocated(BtnColl));

        // Desplazar al centro de la pantalla
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addBtn);

        // Clic directo via JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);
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

  public By modalButton(String modalType) {
    return By.xpath("//button[text()='" + modalType + "']");
  }

  public void clickModalButton(String modalType) {
    click(modalButton(modalType));
  }

  public String getModalTitText() {
    return getText(modalTit);
  }

  public void closeModal() {
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

  public boolean isResetButtonVisible(String buttonName) {
    switch (buttonName) {
      case "Reset":
        return wait.until(ExpectedConditions.visibilityOfElementLocated(resetButton)).isDisplayed();
        case "Logout":
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutBtn)).isDisplayed();

      default:
        throw new IllegalArgumentException("Botón no reconocido: " + buttonName);

    }
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
    //  click(tabNavigationLink(tabName));
    By locator = tabNavigationLink(tabName);
    WebElement element = waitForVisibility(locator);

    // 1. Hacer scroll centrado hacia el elemento
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

    try {
      // 2. Intentar clic estándar de Selenium
      element.click();
    } catch (ElementClickInterceptedException e) {
      // 3. Fallback: Forzar el clic por JavaScript si un banner/overlay lo bloquea
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
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

  // Locador base para los ítems de la lista en la pestaña Sortable
  private By getSortableListItem(String itemText) {
    return By.xpath("//div[@id='demo-tabpane-list']//div[contains(@class,'list-group-item') and normalize-space(text())='" + itemText + "']");
  }

  /**
   * Mueve un elemento de la lista Sortable hasta la posición de otro elemento.
   */
  public void moveSortableItem(String sourceItemText, String targetItemText) throws InterruptedException {
    scrollToTop();
    Thread.sleep(2000);

    WebElement sourceElement = waitForVisibility(getSortableListItem(sourceItemText));
    WebElement targetElement = waitForVisibility(getSortableListItem(targetItemText));

    dragAndDropJS(sourceElement, targetElement);
    Thread.sleep(2000);
  }

  public boolean isItemPositionedBefore(String sourceItemText, String targetItemText) {
    // Buscar los elementos actualizados tras el reordenamiento
    By allItemsLocator = By.xpath("//div[@id='demo-tabpane-list']//div[contains(@class,'list-group-item')]");
    java.util.List<WebElement> items = driver.findElements(allItemsLocator);

    int sourceIndex = -1;
    int targetIndex = -1;

    for (int i = 0; i < items.size(); i++) {
      String text = items.get(i).getText().trim();
      if (text.equals(sourceItemText)) {
        sourceIndex = i;
      }
      if (text.equals(targetItemText)) {
        targetIndex = i;
      }
    }

    return sourceIndex != -1 && targetIndex != -1 && sourceIndex < targetIndex;
  }

  // Locador dinámico para los ítems dentro de la pestaña Grid
  private By getSortableGridItem(String itemText) {
    return By.xpath("//div[@id='demo-tabpane-grid']//div[contains(@class,'list-group-item') and normalize-space(text())='" + itemText + "']");
  }

  /**
   * Cambia a la pestaña Grid en Sortable
   */
  public void clickGridTab() throws InterruptedException {
    WebElement gridTab = waitForVisibility(By.id("demo-tab-grid"));
    scrollToElement(gridTab);
    gridTab.click();
    Thread.sleep(500);
  }

  /**
   * Mueve un elemento en la vista Grid
   */
  public void moveSortableGridItem(String sourceItemText, String targetItemText) throws InterruptedException {
    WebElement sourceElement = waitForVisibility(getSortableGridItem(sourceItemText));
    WebElement targetElement = waitForVisibility(getSortableGridItem(targetItemText));

    dragAndDropJS(sourceElement, targetElement);
    Thread.sleep(1000);
  }

  /**
   * Verifica el orden de los elementos en el Grid
   */
  public boolean isGridItemPositionedBefore(String sourceItemText, String targetItemText) {
    By allGridItemsLocator = By.xpath("//div[@id='demo-tabpane-grid']//div[contains(@class,'list-group-item')]");
    java.util.List<WebElement> items = driver.findElements(allGridItemsLocator);

    int sourceIndex = -1;
    int targetIndex = -1;

    for (int i = 0; i < items.size(); i++) {
      String text = items.get(i).getText().trim();
      if (text.equals(sourceItemText)) {
        sourceIndex = i;
      }
      if (text.equals(targetItemText)) {
        targetIndex = i;
      }
    }

    return sourceIndex != -1 && targetIndex != -1 && sourceIndex < targetIndex;
  }

  // Locador dinámico dinámico para List o Grid según el ID de la pestaña activa
  private By getSelectableItem(String viewType, String itemText) {
    String tabId = viewType.equalsIgnoreCase("Grid") ? "demo-tabpane-grid" : "demo-tabpane-list";
    return By.xpath("//div[@id='" + tabId + "']//li[contains(@class,'list-group-item') and normalize-space(text())='" + itemText + "']");
  }

  /**
   * Selecciona una pestaña por su nombre ("List" o "Grid")
   */
  public void selectTab(String tabName) throws InterruptedException {
    String tabId = tabName.equalsIgnoreCase("Grid") ? "demo-tab-grid" : "demo-tab-list";
    WebElement tab = waitForVisibility(By.id(tabId));
    scrollToElement(tab);
    tab.click();
    Thread.sleep(500);
  }

  /**
   * Selecciona un ítem en List o Grid dentro de Selectable
   */
  public void clickSelectableItem(String viewType, String itemText) {
    WebElement item = waitForVisibility(getSelectableItem(viewType, itemText));
    scrollToElement(item);
    item.click();
  }

  /**
   * Verifica si el ítem de List o Grid está activo/seleccionado
   */
  public boolean isSelectableItemSelected(String viewType, String itemText) {
    WebElement item = waitForVisibility(getSelectableItem(viewType, itemText));
    String classAttribute = item.getAttribute("class");
    return classAttribute != null && classAttribute.contains("active");
  }

  /**
   * Redimensiona la caja (restringida o libre) aplicando un offset en X e Y
   */
  public void resizeBox(String boxType, int xOffset, int yOffset) throws InterruptedException {
    By handleLocator = boxType.equalsIgnoreCase("restricted")
            ? resizableBoxHandleWithRestriction
            : resizableBoxHandle;

    WebElement handle = waitForVisibility(handleLocator);
    scrollToElement(handle);

    int startX = handle.getLocation().getX();
    int startY = handle.getLocation().getY();
    int targetX = startX + xOffset;
    int targetY = startY + yOffset;

    String script =
            "var elem = arguments[0];" +
                    "var startX = arguments[1];" +
                    "var startY = arguments[2];" +
                    "var targetX = arguments[3];" +
                    "var targetY = arguments[4];" +

                    "function fireEvent(type, x, y) {" +
                    "  var evt = new MouseEvent(type, {" +
                    "    bubbles: true, cancelable: true, view: window," +
                    "    clientX: x, clientY: y, buttons: 1" +
                    "  });" +
                    "  elem.dispatchEvent(evt);" +
                    "}" +

                    "fireEvent('mousedown', startX, startY);" +
                    "fireEvent('mousemove', targetX, targetY);" +
                    "fireEvent('mouseup', targetX, targetY);";

    ((JavascriptExecutor) driver).executeScript(script, handle, startX, startY, targetX, targetY);
    Thread.sleep(500);
  }

  /**
   * Obtiene el ancho (Width) de la caja especificada
   */
  public int getBoxWidth(String boxType) {
    By boxLocator = boxType.equalsIgnoreCase("restricted")
            ? resizableBoxWithRestriction
            : resizableBox;

    return driver.findElement(boxLocator).getSize().getWidth();
  }

  /**
   * Obtiene el alto (Height) de la caja especificada
   */
  public int getBoxHeight(String boxType) {
    By boxLocator = boxType.equalsIgnoreCase("restricted")
            ? resizableBoxWithRestriction
            : resizableBox;

    return driver.findElement(boxLocator).getSize().getHeight();
  }

  // Locadores dinámicos para las pestañas de Droppable
  private By getDroppableTab(String tabName) {
    String tabId;
    switch (tabName.toLowerCase()) {
      case "accept":
        tabId = "demo-tab-accept";
        break;
      case "prevent propagation":
        tabId = "demo-tab-preventPropogation";
        break;
      case "revert draggable":
        tabId = "demo-tab-revertable";
        break;
      default:
        tabId = "demo-tab-simple";
        break;
    }
    return By.id(tabId);
  }

  /**
   * Selecciona una pestaña específica en la página Droppable
   */
  public void clickDroppableTab(String tabName) throws InterruptedException {
    WebElement tab = waitForVisibility(getDroppableTab(tabName));
    scrollToElement(tab);
    tab.click();
    Thread.sleep(500);
  }

  /**
   * Arrastra un elemento origen hacia un elemento destino mediante Actions
   */
  public void performDragAndDrop(WebElement source, WebElement target) throws InterruptedException {
    scrollToElement(source);
    Actions actions = new Actions(driver);
    actions.clickAndHold(source)
            .pause(java.time.Duration.ofMillis(300))
            .moveToElement(target)
            .pause(java.time.Duration.ofMillis(300))
            .release()
            .build()
            .perform();
    Thread.sleep(500);
  }

  // --- TAB: ACCEPT ---
  public void dragAcceptItem(String itemName) throws InterruptedException {
    By sourceBy = By.xpath("//div[contains(@class,'drag-box mt-4 ui-draggable') and text()='" + itemName + "']");
    WebElement source = waitForVisibility(sourceBy);

    // 2. Locador de destino correcto dentro del contenedor acceptTab
    By targetBy = By.xpath("//div[contains(@class,'drop-box ui-droppable')]//p[text()='Drop here']");
    WebElement target = waitForVisibility(targetBy);

    performDragAndDrop(source, target);
  }

  public String getAcceptDropBoxText(String expectedText) {
    return waitForVisibility(By.xpath("//p[text()='" + expectedText + "']")).getText().trim();
  }

  // --- TAB: PREVENT PROPAGATION ---
  public void dragToPropagationInnerBox(String innerTargetName) throws InterruptedException {
    scrollToTop();
    Thread.sleep(2000);
    WebElement source = waitForVisibility(By.id("dragBox"));
    String innerId = innerTargetName.contains("not greedy") ? "notGreedyInnerDropBox" : "greedyDropBoxInner";
    WebElement target = waitForVisibility(By.id(innerId));
    performDragAndDrop(source, target);
  }

  public String getPropagationBoxText(String boxName) {
    By locator;
    if (boxName.equals("Inner droppable (not greedy)")) {
      locator = By.id("notGreedyInnerDropBox");
    } else if (boxName.equals("Outer droppable (not greedy)")) {
      locator = By.xpath("//div[@id='notGreedyDropBox']/p");
    } else if (boxName.equals("Inner droppable (greedy)")) {
      locator = By.id("greedyDropBoxInner");
    } else {
      locator = By.xpath("//div[@id='greedyDropBox']/p");
    }
    return waitForVisibility(locator).getText().trim();
  }

  // --- TAB: REVERT DRAGGABLE ---
  public org.openqa.selenium.Point getRevertElementLocation(String elementName) {
    By locator = elementName.equalsIgnoreCase("Will Revert") ? By.id("revertable") : By.id("notRevertable");
    return waitForVisibility(locator).getLocation();
  }

  public void dragRevertItem(String elementName) throws InterruptedException {
    By sourceBy = elementName.equalsIgnoreCase("Will Revert") ? By.id("revertable") : By.id("notRevertable");
    WebElement source = waitForVisibility(sourceBy);
    WebElement target = waitForVisibility(By.xpath("//div[@id='revertableDropContainer']//div[@id='droppable']"));
    performDragAndDrop(source, target);
  }

  public void performDroppableSimple() throws InterruptedException {
    WebElement source = waitForVisibility(By.id("draggable"));
    WebElement target = waitForVisibility(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']"));

    scrollToElement(source);

    Actions actions = new Actions(driver);
    actions.clickAndHold(source)
            .pause(java.time.Duration.ofMillis(300))
            .moveToElement(target)
            .pause(java.time.Duration.ofMillis(300))
            .release()
            .build()
            .perform();

    Thread.sleep(500);
  }

  public String getOuterBoxText(String targetBox) {
    // Retorna el texto del contenedor externo activo
    // Busca el párrafo del contenedor externo donde el texto haya cambiado o esté activo
    By outerNotGreedy = By.xpath("//div[@id='notGreedyDropBox']/p[1]");
    By outerGreedy = By.xpath("//div[@id='greedyDropBox']/p[1]");

    String notGreedyText = waitForVisibility(outerNotGreedy).getText().trim();
    String greedyText = waitForVisibility(outerGreedy).getText().trim();

    // Si la caja not greedy cambió su texto a "Dropped!", devolvemos su texto; si no, devolvemos el de greedy
    if (notGreedyText.contains("Dropped!")) {
      return notGreedyText;
    }
    return greedyText;
  }

  public String getInnerBoxText(String targetBox) {
    // Retorna el texto del contenedor interno
    By innerNotGreedy = By.xpath("//div[@id='notGreedyInnerDropBox']/p");
    By innerGreedy = By.xpath("//div[@id='greedyDropBoxInner']/p");

    String notGreedyText = waitForVisibility(innerNotGreedy).getText().trim();
    String greedyText = waitForVisibility(innerGreedy).getText().trim();

    if (notGreedyText.contains("Dropped!")) {
      return notGreedyText;
    }
    return greedyText;
  }

  public void dragRevertElement(String revertElement) throws InterruptedException {
    WebElement source;
    if (revertElement.equalsIgnoreCase("Will Revert")) {
      source = waitForVisibility(By.id("revertable"));
    } else {
      source = waitForVisibility(By.id("notRevertable"));
    }

    // 1. Guardamos la posición exacta antes del arrastre
    this.initialPosition = source.getLocation();

    WebElement target = waitForVisibility(By.xpath("//div[@id='revertableDropContainer']//div[@id='droppable']"));

    // 2. Ejecución de Drag and Drop manual paso a paso para jQuery UI
    Actions actions = new Actions(driver);
    actions.clickAndHold(source)
            .moveToElement(target)
            .release()
            .build()
            .perform();

    // Pausa técnica para permitir que la animación CSS/jQuery UI se procese
    Thread.sleep(1000);
  }

  public boolean checkRevertBehavior(String revertElement, String expectedBehavior) throws InterruptedException {
    // Pausa para dar tiempo a que finalice la animación de retorno
    Thread.sleep(1000);

    WebElement element;
    if (revertElement.equalsIgnoreCase("Will Revert")) {
      element = waitForVisibility(By.id("revertable"));
    } else {
      element = waitForVisibility(By.id("notRevertable"));
    }

    Point currentPosition = element.getLocation();

    // Medimos la distancia movida respecto a la posición grabada en dragRevertElement
    int deltaX = Math.abs(currentPosition.getX() - this.initialPosition.getX());
    int deltaY = Math.abs(currentPosition.getY() - this.initialPosition.getY());

    // Se considera que volvió a su lugar si se movió menos de 10px en total
    boolean isAtOriginalPosition = (deltaX < 10 && deltaY < 10);

    String behavior = expectedBehavior.toLowerCase().trim();

    if (behavior.contains("not")) {
      // Not Revert: Debe haber cambiado de posición (delta >= 10px)
      return !isAtOriginalPosition;
    } else {
      // Will Revert: Debe estar de vuelta en su posición original (delta < 10px)
      return isAtOriginalPosition;
    }
  }
  public void dragSimpleElementByOffset(String elementName, int xOffset, int yOffset) throws InterruptedException {
    // Locador exacto para la pestaña Simple en DemoQA (id: dragBox)
    WebElement dragBox = waitForVisibility(By.id("dragBox"));

    // Guardamos la posición inicial antes de arrastrar
    this.simpleInitialPosition = dragBox.getLocation();

    // Arrastramos el elemento mediante coordenadas offset
    Actions actions = new Actions(driver);
    actions.dragAndDropBy(dragBox, xOffset, yOffset).perform();

    Thread.sleep(500); // Pausa técnica para permitir el renderizado
  }

  /**
   * Verifica que el elemento se haya desplazado de su posición original
   */
  public boolean verifySimpleElementMoved(String elementName) {
    WebElement dragBox = waitForVisibility(By.id("dragBox"));
    Point currentPosition = dragBox.getLocation();

    // Calculamos el desplazamiento efectuado
    int deltaX = Math.abs(currentPosition.getX() - this.simpleInitialPosition.getX());
    int deltaY = Math.abs(currentPosition.getY() - this.simpleInitialPosition.getY());

    // Retorna true si el elemento se movió al menos 10 píxeles
    return (deltaX >= 10 || deltaY >= 10);
  }
  public void dragAxisElementByOffset(String elementName, int xOffset, int yOffset) throws InterruptedException {
    WebElement element;
    if (elementName.equalsIgnoreCase("Only X")) {
      element = waitForVisibility(By.id("restrictedX"));
      this.initialOnlyXPosition = element.getLocation(); // Guardamos posición inicial de X
    } else if (elementName.equalsIgnoreCase("Only Y")) {
      element = waitForVisibility(By.id("restrictedY"));
      this.initialOnlyYPosition = element.getLocation(); // Guardamos posición inicial de Y
    } else if (elementName.toLowerCase().contains("box")) {
      element = waitForVisibility(By.xpath("//div[@id='containmentWrapper']/div"));
    } else if (elementName.toLowerCase().contains("parent")) {
      element = waitForVisibility(By.xpath("//div[contains(@class,'m-3')]/span"));
    } else {
      throw new IllegalArgumentException("Nombre de elemento no reconocido: " + elementName);
    }

    Actions actions = new Actions(driver);
    actions.dragAndDropBy(element, xOffset, yOffset).perform();

    Thread.sleep(500); // Pausa técnica para permitir actualización en el DOM
  }

  public boolean verifyAxisRestrictionByDirection(String elementName, String expectedDirection) {
    WebElement element;
    Point initialPos;

    if (elementName.equalsIgnoreCase("Only X")) {
      element = waitForVisibility(By.id("restrictedX"));
      initialPos = this.initialOnlyXPosition;
    } else if (elementName.equalsIgnoreCase("Only Y")) {
      element = waitForVisibility(By.id("restrictedY"));
      initialPos = this.initialOnlyYPosition;
    } else {
      return false;
    }

    // Si por alguna razón no se guardó la posición inicial previa al drag, evitamos un NullPointerException
    if (initialPos == null) {
      throw new IllegalStateException("No se registró la posición inicial del elemento '" + elementName + "' antes de arrastrarlo.");
    }

    Point currentPos = element.getLocation();

    int deltaX = Math.abs(currentPos.getX() - initialPos.getX());
    int deltaY = Math.abs(currentPos.getY() - initialPos.getY());

    if (expectedDirection.equalsIgnoreCase("left and right")) {
      // "Only X": Debe haberse movido significativamente en horizontal (deltaX > 5)
      // y haberse mantenido prácticamente fijo en vertical (deltaY <= 3)
      return (deltaX > 5) && (deltaY <= 3);

    } else if (expectedDirection.equalsIgnoreCase("up and down")) {
      // "Only Y": Debe haberse movido significativamente en vertical (deltaY > 5)
      // y haberse mantenido prácticamente fijo en horizontal (deltaX <= 3)
      return (deltaY > 5) && (deltaX <= 3);
    }

    return false;
  }

  // Método auxiliar para convertir cadenas como "-67px" o "0px" o "auto" a double
  private double parseCssPx(String cssValue) {
    if (cssValue == null || cssValue.equals("auto") || cssValue.isEmpty()) {
      return 0.0;
    }
    try {
      return Double.parseDouble(cssValue.replace("px", "").trim());
    } catch (NumberFormatException e) {
      return 0.0;
    }
  }

  public boolean verifyElementWithinContainer(String elementName) {
    WebElement element;
    WebElement container;

    if (elementName.toLowerCase().contains("box")) {
      element = waitForVisibility(By.xpath("//div[@id='containmentWrapper']/div"));
      container = waitForVisibility(By.id("containmentWrapper"));
    } else {
      element = waitForVisibility(By.xpath("//div[contains(@class,'m-3')]/span"));
      container = element.findElement(By.xpath("..")); // Contenedor padre directo
    }

    // Coordenadas y dimensiones del elemento
    Point elemLoc = element.getLocation();
    Dimension elemSize = element.getSize();

    // Coordenadas y dimensiones del contenedor
    Point containerLoc = container.getLocation();
    Dimension containerSize = container.getSize();

    // Comprobamos que todos los bordes del elemento estén dentro del contenedor (con 2px de margen por bordes)
    boolean isInsideX = (elemLoc.getX() >= containerLoc.getX() - 2) &&
            ((elemLoc.getX() + elemSize.getWidth()) <= (containerLoc.getX() + containerSize.getWidth() + 2));

    boolean isInsideY = (elemLoc.getY() >= containerLoc.getY() - 2) &&
            ((elemLoc.getY() + elemSize.getHeight()) <= (containerLoc.getY() + containerSize.getHeight() + 2));

    return isInsideX && isInsideY;
  }
  public void dragCursorElementByOffset(String elementName, int xOffset, int yOffset) throws InterruptedException {
    WebElement element;

    if (elementName.equalsIgnoreCase("I will always stick to the center")) {
      element = waitForVisibility(By.id("cursorCenter"));
    } else if (elementName.equalsIgnoreCase("My cursor is at top left")) {
      element = waitForVisibility(By.id("cursorTopLeft"));
    } else if (elementName.equalsIgnoreCase("My cursor is at bottom")) {
      element = waitForVisibility(By.id("cursorBottom"));
    } else {
      throw new IllegalArgumentException("Elemento de cursor no válido: " + elementName);
    }

    // 1. Guardamos la posición inicial exacta antes de mover
    this.cursorInitialPosition = element.getLocation();

    // 2. Hacemos scroll hacia el elemento para asegurar visibilidad en pantalla
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    Thread.sleep(300);

    // 3. Ejecutamos la secuencia con micropausas para que los event listeners de React detecten el drag
    Actions actions = new Actions(driver);
    actions.clickAndHold(element)
            .pause(Duration.ofMillis(200))
            .moveByOffset(xOffset, yOffset)
            .pause(Duration.ofMillis(200))
            .release()
            .build()
            .perform();

    Thread.sleep(500); // Pausa técnica para permitir actualización de coordenadas en el DOM

  }

  /**
   * Verifica que el elemento de la pestaña 'Cursor Style' haya cambiado de posición
   */
  public boolean verifyCursorElementMoved(String elementName) {
    WebElement element;

    if (elementName.equalsIgnoreCase("I will always stick to the center")) {
      element = waitForVisibility(By.id("cursorCenter"));
    } else if (elementName.equalsIgnoreCase("My cursor is at top left")) {
      element = waitForVisibility(By.id("cursorTopLeft"));
    } else if (elementName.equalsIgnoreCase("My cursor is at bottom")) {
      element = waitForVisibility(By.id("cursorBottom"));
    } else {
      return false;
    }

    Point currentPos = element.getLocation();

    int deltaX = Math.abs(currentPos.getX() - this.cursorInitialPosition.getX());
    int deltaY = Math.abs(currentPos.getY() - this.cursorInitialPosition.getY());

    // Confirma que el elemento se haya desplazado al menos 10px en cualquier dirección
    return (deltaX >= 10 || deltaY >= 10);
  }

  /**
   * Verifica si la página/formulario de Registro está visible
   */
  public boolean isRegisterPageDisplayed(String expectedHeading) {
    scrollToTop();
    WebElement header = waitForVisibility(mainHeader);
    return header.getText().equalsIgnoreCase(expectedHeading);
  }

  /**
   * Completa el formulario de registro de usuario
   */
  public void fillRegisterForm(String firstName, String lastName, String userName, String password) {
    WebElement fName = waitForVisibility(firstNameInpu);
    fName.clear();
    fName.sendKeys(firstName);

    WebElement lName = waitForVisibility(lastNameInpu);
    lName.clear();
    lName.sendKeys(lastName);

    WebElement uName = waitForVisibility(userNameInput);
    uName.clear();
    uName.sendKeys(userName);

    WebElement pwd = waitForVisibility(passwordInput);
    pwd.clear();
    pwd.sendKeys(password);
  }

  /**
   * Valida la interacción con el botón Register
   */

  public void fillLoginForm(String userName, String password) {
    WebElement uName = waitForVisibility(userNameInput);
    uName.clear();
    uName.sendKeys(userName);

    WebElement pwd = waitForVisibility(passwordInput);
    pwd.clear();
    pwd.sendKeys(password);
  }
  public String getLoggedInUsername() {
    WebElement label = waitForVisibility(userNameValueLabel);
    return label.getText().trim();
  }
  public boolean isButtonVisible(String buttonName) {
    if (buttonName.equalsIgnoreCase("Logout") || buttonName.equalsIgnoreCase("Log out")) {
      return waitForVisibility(logoutBtn).isDisplayed();
    } else if (buttonName.equalsIgnoreCase("Login")) {
      return waitForVisibility(loginBtn).isDisplayed();
    }
    return false;
  }
  public void selectMenuOption(String optionName) {
    WebElement menuElement;
    if (optionName.equalsIgnoreCase("Book Store")) {
      menuElement = waitForVisibility(bookStoreMenuOption);
    } else if (optionName.equalsIgnoreCase("Profile")) {
      menuElement = waitForVisibility(profileMenuOption);
    } else if (optionName.equalsIgnoreCase("Book Store API")) {
      menuElement = waitForVisibility(bookStoreApiMenuOption);
    } else {
      throw new IllegalArgumentException("Opción de menú no encontrada: " + optionName);
    }

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuElement);
    menuElement.click();
  }

  /**
   * Ingresa un texto de búsqueda en la caja 'Type to search'
   */
  public void searchBook(String text) {
    scrollToTop();
    WebElement input = waitForVisibility(searchBox);
    input.clear();
    input.sendKeys(text);
  }

  /**
   * Verifica que un libro específico con su autor exista en la tabla filtrada
   */
  public boolean isBookPresentInTable(String expectedTitle, String expectedAuthor) {
    List<WebElement> rows = driver.findElements(booksTableRows);

    for (WebElement row : rows) {
      String rowText = row.getText();
      if (rowText.contains(expectedTitle) && rowText.contains(expectedAuthor)) {
        return true;
      }
    }
    return false;
  }
  public String getNotLoggedInMessage() {
    scrollToTop();
    WebElement element = waitForVisibility(notLoggedInLabel);
    return element.getText().trim().replaceAll("\\s+", " ");
  }
  public void clickBookTitle(String bookTitle) {
    By bookLink = By.xpath("//a[contains(text(),'" + bookTitle + "')]");
    WebElement element = waitForVisibility(bookLink);
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    element.click();
  }
  public boolean isBookPresentInProfile(String expectedTitle) {
    By bookLocator = By.xpath("//a[contains(text(), '" + expectedTitle + "')]");

    try {
      // 2. Esperamos hasta 10 segundos a que el libro sea visible en la tabla del perfil
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      WebElement bookElement = wait.until(ExpectedConditions.visibilityOfElementLocated(bookLocator));

      // 3. Hacemos scroll al elemento para confirmar su visibilidad
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", bookElement);

      return bookElement.isDisplayed();
    } catch (Exception e) {
      // Si se agota el tiempo y no aparece el elemento
      return false;
    }
  }
  public void deleteBookByTitle(String bookTitle) {
    By deleteBtnLocator = By.xpath("//a[contains(text(),'" + bookTitle + "')]/ancestor::tr//span[@title='Delete']");

    WebElement deleteBtn = wait.until(ExpectedConditions.presenceOfElementLocated(deleteBtnLocator));

    // Scroll y clic por JS para evitar problemas de interceptación
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", deleteBtn);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);

    // 2. Confirmar en el modal emergente de la página
    By confirmOkModal = By.id("closeSmallModal-ok");
    WebElement okBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmOkModal));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", okBtn);

    // 3. Aceptar la alerta emergente que notifica la eliminación
    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    alert.accept();
  }

  // Método para cerrar la sesión
  public void clickLogout() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    By logoutBtnLocator = By.xpath("//button[text()='Log out' or @id='submit']");
    WebElement logoutBtn = wait.until(ExpectedConditions.presenceOfElementLocated(logoutBtnLocator));

    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", logoutBtn);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutBtn);
  }
}

