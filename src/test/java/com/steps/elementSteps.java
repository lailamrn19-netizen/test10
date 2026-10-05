package com.steps;

import com.pages.BasePage;
import com.pages.cargaPage;
import com.pages.elementPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class elementSteps {
    private final elementPage elementPage = new elementPage();
    @Given("the user navigates to the complex elements section")
    public void theUserNavigatesToTheComplexElementsSection() {
        // Lógica de navegación a la sección de elementos complejos
    }

    @When("the user selects the category {string} from the native select")
    public void theUserSelectsTheCategoryFromTheNativeSelect(String category) {
        elementPage.selectNativeCategory(category);
    }

    @And("the user selects the region {string} from the custom dropdown")
    public void theUserSelectsTheRegionFromTheCustomDropdown(String region) {
        elementPage.selectCustomRegionOption(region);
    }

    @Then("the selected category should be {string}")
    public void theSelectedCategoryShouldBe(String expectedCategory) {
        String actualCategory = elementPage.getSelectedNativeCategory();
        Assert.assertEquals(actualCategory, expectedCategory, "La categoría seleccionada no coincide.");
    }

    @And("the region dropdown button should display {string}")
    public void theRegionDropdownButtonShouldDisplay(String expectedRegion) {
        String actualButtonText = elementPage.getCustomRegionButtonText();
        Assert.assertEquals(actualButtonText, expectedRegion, "El texto del botón desplegable no coincide.");
    }

    @And("the status message should contain {string}")
    public void theStatusMessageShouldContain(String expectedMessage) {
        String actualResult = elementPage.getStatusMessageText().trim();
        boolean containsMessage = actualResult.toLowerCase().contains(expectedMessage.toLowerCase());

        Assert.assertTrue(
                containsMessage,
                "El mensaje de estado [" + actualResult + "] no contiene la región [" + expectedMessage + "]."
        );
    }

    @Then("the dynamic status message should display {string}")
    public void theDynamicStatusMessageShouldDisplay(String expectedMessage) {
        String actualMessage = elementPage.getDynamicStatusMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "El mensaje de estado dinámico no coincide.");
    }

    @When("the user selects the dynamic checkbox {string}")
    public void theUserSelectsTheDynamicCheckbox(String label) {
        elementPage.clickCheckboxByLabel(label);
    }

    @When("the user selects the dynamic radio button {string}")
    public void theUserSelectsTheDynamicRadioButton(String label) {
        elementPage.clickRadioButtonByLabel(label);
    }

    @Then("the dynamic checkbox {string} should be selected")
    public void theDynamicCheckboxShouldBeSelected(String label) {
        Assert.assertTrue(elementPage.isCheckboxSelected(label), "El checkbox " + label + " no está seleccionado.");
    }

    @And("the dynamic radio button {string} should be selected")
    public void theDynamicRadioButtonShouldBeSelected(String label) {
        Assert.assertTrue(elementPage.isRadioButtonSelected(label), "El radio button " + label + " no está seleccionado.");
    }

    @And("the user clicks the {string} button {int} times")
    public void theUserClicksTheButtonTimes(String buttonName, int times) {
        for (int i = 0; i < times; i++) {
            elementPage.clickButton(buttonName);

        }
    }

    @And("the user clicks the {string}")
    public void theUserClicksTheButton(String buttonName) {
        elementPage.clickButton(buttonName);
    }
    @And("the user enters the name {string} in the iframe input")
    public void theUserEntersTheNameInTheIframeInput(String name) {
        elementPage.enterIframeName(name);
    }

    @Then("a greeting message {string} should be displayed inside the iframe")
    public void aGreetingMessageShouldBeDisplayedInsideTheIframe(String expectedGreeting) {
       Assert.assertEquals(elementPage.getIframeGreetingText(), expectedGreeting);
    }
    @When("the user enters the secret code {string} in the shadow DOM input")
    public void theUserEntersTheSecretCodeInTheShadowDOMInput(String code) {
        elementPage.enterShadowCode(code);
    }

    @Then("the shadow DOM result message should display {string}")
    public void theShadowDOMResultMessageShouldDisplay(String expectedMessage) {
        // Si el mensaje esperado termina en espacios o está vacío tras 'Código recibido: '
        if (expectedMessage.equalsIgnoreCase("Código recibido: ") || expectedMessage.equalsIgnoreCase("Código recibido:")) {
            expectedMessage = "Código recibido: (vacío)";
        }
        String actualMessage = elementPage.getShadowResultMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "El mensaje con el código recibido no coincide.");
    }
    @Then("the modal {string} should be displayed")
    public void theModalShouldBeDisplayed(String expectedTitle) {
        Assert.assertTrue(elementPage.isModalDisplayed(expectedTitle),
                "El modal con título '" + expectedTitle + "' no se muestra.");
    }

    @When("the user enters {string} in the modal input")
    public void theUserEntersInTheModalInput(String text) {
        elementPage.enterTextInModal(text);
    }

    @Then("the modal should be closed")
    public void theModalShouldBeClosed() {
        Assert.assertTrue(elementPage.isModalClosed(),
                "El modal sigue visible y debería haberse cerrado.");
    }

    @Then("the saved result message should display {string}")
    public void theSavedResultMessageShouldDisplay(String expectedMessage) {
        String actualMessage = elementPage.getSavedResultMessage();
        Assert.assertEquals(actualMessage, expectedMessage,
                "El mensaje del resultado guardado no coincide.");
    }
    @Then("the action result message should display {string}")
    public void theActionResultMessageShouldNotDisplay(String expectedMessage) {
        String actualMessage = elementPage.getBackgroundActionResultMessage();
        Assert.assertNotEquals(actualMessage, expectedMessage, "El botón de fondo se pudo pulsar a pesar de tener el modal abierto.");
    }
}
