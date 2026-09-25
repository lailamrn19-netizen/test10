package com.steps;

import com.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.types.DataTable;
import org.testng.Assert;
import java.util.Map;

import java.awt.*;

public class LoginSteps {

  private final LoginPage loginPage = new LoginPage();
    private String actualAlertResult;

  @Given("the user navigates to the main page")
  public void navigateToMainPage() {
    loginPage.navigateToMainPage();
  }
  @When("the user selects the {string} card from the main page")
    public void selectCategoryCard(String categoryName) {
        loginPage.selectCategoryCard(categoryName);
  }

  @And("the user clicks on the element option {string}")
  public void clickOnMenuItems(String option) {
    loginPage.clickMenuItems(option);
 }
  @Then("the user should be redirected to the {string} page")
  public void verifyRedirectToMenu(String expectedUrl){
    String actualUrl = loginPage.getCurrentUrl();
    Assert.assertTrue(actualUrl.contains(expectedUrl),
            "Se esperaba que la URL contuviera '" + expectedUrl + "', pero se obtuvo: " + actualUrl);
  }
  @When("the user enters the full name {string}")
  public void enterFullName(String fullName) {
    loginPage.enterFullName(fullName);
  }
  @When("the user enters the email {string}")
  public void enterEmail(String email) {
    loginPage.enterEmail(email);
  }
  @When("the user enters the current address {string}")
  public void  enterCurrentAddress(String address) {
    loginPage.enterCurrentAddress(address);
  }
  @When("the user enters the permanent address {string}")
  public void enterPermanentAddress(String address) {
    loginPage.enterPermanentAddress(address);
  }
  @When("the user clicks the submit button")
  public void clickSubmit(){
    loginPage.clickSubmit();
  }
  @Then("the output details should be displayed below the form")
  public void verifyOutput(){
    Assert.assertTrue(loginPage.isOutputDisplayed(), "El cuadro de resultados no se mostró.");
  }
  @And("the user expands all folders")
  public void expandAllTreeFolders() {
    loginPage.expandAllTreeFolders();
    loginPage.expandFolders("Desktop");
    loginPage.expandFolders("Documents");
    loginPage.expandFolders("Downloads");
    loginPage.expandFolders("WorkSpace");
    loginPage.expandFolders("Office");
  }
  @And("the user selects the {string} checkboxes")
  public void selectCheckboxes(String elements) {
      // Separa la cadena por comas en caso de que vengan múltiples elementos
      String[] elementList = elements.split(",");

      for (String item : elementList) {
          // .trim() elimina espacios en blanco adicionales
          loginPage.selectCheckboxByName(item.trim());
      }
  }

  @Then("the result area should display {string} as selected")
  public void verifyResultText(String expectedResultText) {
      // Obtiene el texto del contenedor en la interfaz
   String actualText = loginPage.getResultText();

      // Normaliza el texto quitando saltos de línea (\n) y espacios dobles
   String normalizedActualText = actualText.replace("\n", " ").replaceAll("\\s+", " ").trim();

      // Realiza la aserción comprobando si contiene el texto esperado
   Assert.assertTrue(
        normalizedActualText.contains(expectedResultText),
           "Se esperaba encontrar '" + expectedResultText + "' en el resultado pero se obtuvo: " + normalizedActualText
    );
  }
  @And("the user selects the {string} radio button")
  public void selectRadioButton(String optionName) {
       loginPage.selectRadioButtonByName(optionName);
   }
  @And("the user clicks on the {string} button")
  public void clickAddButton(String buttonName) {
       loginPage.clickAddButton();
   }
  @And("the user fills the registration form with {string},{string},{string},{string},{string}, and {string}")
  public void fillForm(String firstName, String lastName, String email, String age, String salary, String department) {
        loginPage.fillRegistrationForm(firstName, lastName, email, age, salary, department);
   }
  @And("the user clicks on the submit button")
  public void clickSubmitButton() {
        loginPage.clickSubmitButton();
   }
  @Then("the table should display the user with email {string} in the list")
  public void verifyRecordAdded(String email) {
    Assert.assertTrue(loginPage.isUserPresentInTable(email), "El usuario con email " + email + " no se encuentra en la tabla");
   }

  @And("the user searches for {string} in the search box")
  public void searchUser(String keyword) {
        loginPage.searchInTable(keyword);
   }
  @Then("the table should contain {string} in the results")
   public void verifySearchResult(String keyword) {
     Assert.assertTrue(loginPage.isUserPresentInTable(keyword), "No se encontró el término " + keyword + " en los resultados");
   }
  @And("the user performs a {string} click on the {string} button")
   public void userPerformsClickOnButton(String actiontype , String buttonName) {
      switch (actiontype.toLowerCase()){
          case "double":
              loginPage.performDoubleClick();
              break;
          case "right":
              loginPage.performRightClick();
              break;
          case "dynamic":
              loginPage.performDynamicClick(buttonName);
              break;
          default:
              throw new IllegalArgumentException("Acción no soportada: " + buttonName);
      }
  }
  @Then("the message {string} should be displayed")
   public void verifyMessageDisplayed(String expectedMessage) {
       Assert.assertTrue(
           loginPage.isMessageDispalay(expectedMessage), "El mensaje esperado no coincide o no es visible.");

    }
  @And("the user clicks on the {string} link")
   public void clickLink(String linkName) {
     loginPage.clickLinkByText(linkName);
   }
  @Then("a new tab should be opened with URL containing {string}")
   public void verifyNewTabUrl(String expectedUrl) {
     Assert.assertTrue(
        loginPage.isNewTabOpenedWithUrl(expectedUrl),
       "La nueva pestaña no contiene la URL esperada: " + expectedUrl
       );
    }
  @Then("the response message should display status code {string} and status text {string}")
   public void verifyApiResponseMessage(String statusCode, String statusText) throws InterruptedException {

        Assert.assertTrue(
              loginPage.isApiResponseDisplayed(statusCode, statusText),
              "El mensaje no contiene el código: " + statusCode + " o texto: " + statusText);

  }
  @Then("the valid image should be displayed correctly")
   public void verifyValidImage() {
        Assert.assertTrue(
                loginPage.isValidImageDisplayed(),
                "La imagen válida no se está mostrando correctamente."
        );
    }

  @Then("the broken image should not be displayed correctly")
   public void verifyBrokenImage() {
        Assert.assertTrue(
                loginPage.isBrokenImageDisplayed(),
                "La imagen rota se cargó correctamente cuando debería estar rota."
        );
    }

  @Then("the user should be redirected to the main page")
   public void verifyMainPageRedirection() {
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertEquals(
                currentUrl,
                "https://demoqa.com/",
                "El usuario no fue redirigido a la página principal."
        );
    }

  @When("the user navigates back")
   public void navigateBack() {
        loginPage.navigateBack();
   }

  @Then("the response status code should be {string}")
   public void verifyStatusCode(String expectedCode) {
        Assert.assertTrue(
                loginPage.isStatusCodeErrorDisplayed(expectedCode),
                "No se muestra el mensaje de código de estado " + expectedCode + " en la página."
        );
   }
  @And("the user clicks on the download button")
    public void clickDownloadButton() {
      loginPage.clickDownloadButton();
  }
  @Then("the file {string} should be downloaded")
    public void verifyDownloadedFile(String FileName) {
      Assert.assertTrue(
              loginPage.isFileDownloaded(FileName),
              "El archivo "+FileName+" no se descargo correctamente."
      );
  }
  @When("the user uploads the file {string}")
    public void uploadFile(String fileName) {
      String absolutePath = new java.io.File(System.getProperty("user.home") + "/Downloads/" + fileName).getAbsolutePath();
      loginPage.uploadFile(absolutePath);
  }
  @Then("the uploaded file path should display {string}")
    public void verifyUploadedFilePath(String expectedPath) {
        String actualPath = loginPage.getUploadedFilePathText();
        Assert.assertTrue(
                actualPath.contains(expectedPath),
                "La ruta mostrada en pantalla '" + actualPath + "' no contiene '" + expectedPath + "'."
        );
  }
  @Then("the button {string} should be enabled after {int} seconds")
    public void verifyButtonEnabledAfterWait(String buttonName, int seconds) {
        Assert.assertTrue(
                loginPage.isButtonEnabledAfterWait(seconds + 1),
                "El botón '" + buttonName + "' no se habilitó tras los " + seconds + " segundos."
        );
  }

  @And("the button {string} should change text color")
    public void verifyButtonColorChange(String buttonName) {
        Assert.assertTrue(
                loginPage.isColorChanged(),
                "El color del botón '" + buttonName + "' no cambió."
        );
  }

  @And("the button {string} should be displayed after {int} seconds")
    public void verifyButtonVisibleAfterWait(String buttonName, int seconds) {
        Assert.assertTrue(
                loginPage.isButtonVisibleAfterWait(seconds + 1),
                "El botón '" + buttonName + "' no fue visible tras los " + seconds + " segundos."
        );
  }
  @And("the user fills the registration form with the following details:")
    public void fillRegistrationForm(Map<String, String> formData) {
            loginPage.fillForm(formData);
  }
  @And("the user submits the form")
    public void submitForm() {
        loginPage.clickSubmit();
  }
  @Then("the submission modal should be displayed with title {string}")
   public void verifySubmissionModal(String expectedTitle) {
        String actualTitle = loginPage.getModalTitleText();
        Assert.assertEquals(actualTitle, expectedTitle, "El modal de confirmación no coincide.");
  }
  @And("the user click on the {string} buttons")
    public void clickButtonByName(String buttonName) {
        loginPage.clickButtonByName(buttonName);
  }
  @Then("a new tab or window should open with the heading {string}")
    public void verifyNewWindowHeading(String expectedHeading) {
        String actualHeading = loginPage.getHeadingFromNewWindow();
        Assert.assertEquals(actualHeading, expectedHeading, "El encabezado en la nueva ventana no coincide.");
  }
  @And("the user triggers the alert for {string}")
    public void userTriggersAlert(String alertType) {
      loginPage.clickAlertButton(alertType);
  }

  @And("the user interacts with the alert for {string} with text {string}")
    public void userInteractsWithAlert(String alertType, String inputText) {
      this.actualAlertResult = loginPage.interactWithAlert(alertType, inputText);
  }

  @Then("the alert message or result should be {string}")
    public void verifyAlertMessage(String expectedResult) {
      Assert.assertEquals(this.actualAlertResult, expectedResult, "El resultado no coincide con el esperado.");
  }
  @And("the user switches to the iframe {string}")
    public void userSwitchesToIframe(String frameId) {
        loginPage.switchToIframeById(frameId);
  }

  @Then("the heading text inside iframe should be {string}")
    public void verifyIframeHeading(String expectedHeading) {
        String actualHeading = loginPage.getIframeHeadingText();
        Assert.assertEquals(actualHeading, expectedHeading, "El texto dentro del iframe no coincide.");
   }

  @And("the user switches back to the main content")
    public void userSwitchesBackToMainContent() {
        loginPage.switchToDefaultContent();
  }
  @And("the user switches to the parent frame")
    public void userSwitchesToParentFrame() {
        loginPage.switchToParentFrame();
  }

  @Then("the parent frame text should be {string}")
    public void verifyParentFrameText(String expectedText) {
        String actualText = loginPage.getParentFrameText();
        Assert.assertTrue(actualText.contains(expectedText), "El texto del Parent Frame no coincide.");
  }

  @When("the user switches to the child frame inside the parent frame")
    public void userSwitchesToChildFrame() {
        loginPage.switchToChildFrame();
  }

  @Then("the child frame text should be {string}")
    public void verifyChildFrameText(String expectedText) {
        String actualText = loginPage.getChildFrameText();
        Assert.assertEquals(actualText, expectedText, "El texto del Child Frame no coincide.");
  }
  @And("the user clicks on the {string} modal button")
    public void userClicksOnModalButton(String modalType) {
        loginPage.clickModalButton(modalType);
  }

  @Then("the modal window should display the title {string}")
    public void verifyModalTitle(String expectedTitle) {
        String actualTitle = loginPage.getModalTitText();
        Assert.assertEquals(actualTitle, expectedTitle, "El título del modal no es el esperado.");
  }

  @When("the user closes the modal window")
    public void userClosesModal() {
        loginPage.closeModal();
  }

  @Then("the modal window should no longer be visible")
    public void verifyModalIsClosed() {
        Assert.assertTrue(loginPage.isModalClosed(), "El modal sigue visible en pantalla.");
  }
  @When("the user expands the accordian section {string}")
    public void userExpandsAccordianSection(String sectionHeading) {
        loginPage.clickAccordianSection(sectionHeading);
  }

  @Then("the content for section {string} should be visible")
    public void verifyAccordianContentIsVisible(String sectionHeading) {
        Assert.assertTrue(loginPage.isAccordianContentVisible(sectionHeading), "El contenido de la sección no se desplegó.");
  }

  @And("the content for section {string} should start with {string}")
    public void verifyAccordianContentText(String sectionHeading, String expectedContent) {
        // Se pasa el título de la sección ("What is Lorem Ipsum?") al metodo
        String actualText = loginPage.getAccordianContentText(sectionHeading);
        Assert.assertTrue(actualText.contains(expectedContent), "El texto del acordeón no contiene la cadena esperada.");
  }
  @When("the user types {string} in the {string} auto complete field")
    public void userTypesInAutoCompleteField(String inputText, String fieldType) {
        loginPage.typeInAutoCompleteField(fieldType, inputText);
  }

  @And("the user selects {string} from the auto complete suggestion list")
    public void userSelectsFromAutoCompleteList(String colorName) {
        loginPage.selectAutoCompleteOption(colorName);
  }

  @Then("the selected color {string} should be displayed in the {string} field")
    public void verifySelectedColorInField(String colorName, String fieldType) {
        Assert.assertTrue(loginPage.isColorDisplayedInField(fieldType, colorName),
                "El color " + colorName + " no se muestra en el campo " + fieldType);
  }
  @When("the user inputs {string} in the {string} field")
    public void userInputInDatePickerField(String value, String fieldType) {
        loginPage.inputDatePickerValue(fieldType, value);
  }

  @Then("the {string} field should contain {string}")
    public void verifyDatePickerFieldValue(String fieldType, String expectedValue) {
        String actualValue = loginPage.getDatePickerValue(fieldType);
        Assert.assertEquals(actualValue, expectedValue, "El valor del campo " + fieldType + " no coincide.");
  }
  @When("the user sets the slider value to {string}")
    public void userSetsSliderValue(String targetValue) {
        loginPage.setSliderValue(targetValue);
  }

  @Then("the slider value field should display {string}")
    public void verifySliderValue(String expectedValue) {
        String actualValue = loginPage.getSliderValue();
        Assert.assertEquals(actualValue, expectedValue, "El valor del slider no coincide con el esperado.");
  }
  @When("the user clicks the {string} button on the progress bar page")
    public void userClicksStartProgressBar(String buttonName) {
        loginPage.clickStartProgressBar();
  }

  @Then("the progress bar should reach {string}")
    public void verifyProgressBarReachesPercentage(String expectedPercentage) {
        boolean isReached = loginPage.waitForProgressBarValue(expectedPercentage);
        Assert.assertTrue(isReached, "La barra de progreso no alcanzó el " + expectedPercentage);
  }

  @And("the {string} button should be visible")
    public void verifyResetButtonIsVisible(String buttonName) {
        Assert.assertTrue(loginPage.isResetButtonVisible(), "El botón Reset no está visible.");
  }
  @When("the user clicks on the {string} tab")
    public void userClicksOnTab(String tabName) {
        loginPage.clickTab(tabName);
  }

  @Then("the tab panel for {string} should be active")
    public void verifyTabIsActive(String tabName) {
        Assert.assertTrue(loginPage.isTabActive(tabName), "La pestaña " + tabName + " no se encuentra activa.");
  }

  @And("the content for tab {string} should start with {string}")
    public void verifyTabContentText(String tabName, String expectedContent) {
        String actualText = loginPage.getTabContentText(tabName);
        Assert.assertTrue(actualText.contains(expectedContent), "El contenido de la pestaña " + tabName + " no coincide.");
  }
  @When("the user hovers over the {string} element")
    public void userHoversOverElement(String elementName) {
        loginPage.hoverOverElement(elementName);
  }

  @Then("a tooltip with text {string} should be displayed")
    public void verifyTooltipText(String expectedTooltip) {
        String actualTooltip = loginPage.getTooltipText();
        Assert.assertEquals(actualTooltip, expectedTooltip, "El texto del tooltip no coincide con el esperado.");
  }
  @When("the user hovers over the main menu item {string}")
    public void theUserHoversOverTheMainMenuItem(String mainItem) {
        loginPage.hoverOverMenuItem(mainItem);
  }

    // Reutiliza hoverOverMenuItem de tu LoginPage para el submenú
  @And("the user hovers over the sub-menu item {string}")
    public void theUserHoversOverTheSubMenuItem(String subItem) {
        loginPage.hoverOverMenuItem(subItem);
  }

    // Reutiliza clickSubSubMenuItem de tu LoginPage
  @And("the user clicks on the sub-sub-menu item {string}")
    public void theUserClicksOnTheSubSubMenuItem(String subSubItem) {
        loginPage.clickSubSubMenuItem(subSubItem);
  }

    // Validación básica reutilizando getCurrentUrl() de BasePage
  @Then("the sub-sub-menu item {string} action should be executed")
    public void theSubSubMenuItemActionShouldBeExecuted(String subSubItem) {
      String currentUrl = loginPage.getCurrentUrl();
      Assert.assertTrue(currentUrl.endsWith("#") || currentUrl.contains("menu#"),
              "El clic en " + subSubItem + " no ejecutó la acción esperada (URL actual: " + currentUrl + ")");
    }
  @When("the user selects {string} from the {string} dropdown")
    public void theUserSelectsFromDropdown(String optionValue, String dropdownName) throws InterruptedException {
        loginPage.selectOptionInSelectMenu(dropdownName, optionValue);
        Thread.sleep(2000);
  }

  @Then("the selected value {string} should be selected in {string}")
    public void theSelectedValueShouldBeSelectedIn(String expectedOption, String dropdownName) {
        if ("Standard multi select".equals(dropdownName)) {
            boolean isSelected = loginPage.isStandardOptionSelected(expectedOption);
            Assert.assertTrue(isSelected, "La opción " + expectedOption + " no fue seleccionada en Standard multi select.");
        }
  }
}
