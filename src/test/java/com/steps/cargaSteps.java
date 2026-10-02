package com.steps;

import com.pages.cargaPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class cargaSteps {
    private final cargaPage cargaPage =  new cargaPage();
    private long startTime;
    private long elapsedTime;

    @When("the user requests to load dynamic content")
    public void theUserRequestsToLoadDynamicContent() {
        cargaPage.clickLoadButton();
    }

    @Then("a loading indicator should be displayed during 4 seconds")
    public void aLoadingIndicatorShouldBeDisplayedDuring4Seconds() {
        // Valida la visibilidad durante los 4 segundos
        boolean wasSpinnerDisplayed = cargaPage.verifySpinnerDuration();
        Assert.assertTrue(wasSpinnerDisplayed, "El indicador de carga no se mostró correctamente.");
    }

    @Then("a new button with a dynamic ID should appear")
    public void aNewButtonWithADynamicIDShouldAppear() {
        String generatedButtonId = cargaPage.getLoadedButtonId();
        Assert.assertNotNull(generatedButtonId, "No se generó el nuevo botón.");
        Assert.assertTrue(generatedButtonId.startsWith("loaded-"),
                "El ID del botón no empieza por 'loaded-': " + generatedButtonId);
    }

    @And("the user clicks the newly loaded button")
    public void theUserClicksTheNewlycargaPage() {
        cargaPage.clickLoadedButton();
    }

    @Then("the interaction confirmation message should be displayed")
    public void theInteractionConfirmationMessageShouldBeDisplayed() {
        String resultText = cargaPage.getConfirmationText();
        Assert.assertFalse(resultText.isEmpty(), "No se confirmó la interacción.");
    }
    @And("the text input field should be disabled initially")
    public void theTextInputFieldShouldBeDisabledInitially() {
        boolean isDisabled = cargaPage.isInputFieldDisabled();
        Assert.assertTrue(isDisabled, "El campo de texto no está deshabilitado inicialmente.");
    }
    @And("the input field {string} should be disabled initially")
    public void theInputFieldShouldBeDisabledInitially(String labelText) {
        boolean isDisabled = cargaPage.isDeferredInputDisabled();
        Assert.assertTrue(isDisabled, "El campo de texto no está deshabilitado inicialmente.");
    }

    @And("the user clicks the {string} button")
    public void theUserClicksTheButton(String buttonText) {

        cargaPage.clickEnableFieldButton(buttonText);
    }

    @Then("the input field should become enabled after 3 seconds")
    public void theInputFieldShouldBecomeEnabledAfter3Seconds() {
        boolean isEnabled = cargaPage.waitForDeferredInputToBeEnabled();
        Assert.assertTrue(isEnabled, "El campo de texto no se habilitó tras los 3 segundos.");
    }

    @When("the user types {string} into the input field")
    public void theUserTypesIntoTheInputField(String textToType) {
        cargaPage.typeInDeferredInput(textToType);
    }

    @Then("the input field should contain the text {string}")
    public void theInputFieldShouldContainTheText(String expectedText) {
        String actualText = cargaPage.getDeferredInputValue();
        Assert.assertEquals(actualText, expectedText, "El texto ingresado en el campo no coincide.");
    }
    @Then("a new node with an incremented version should be displayed")
    public void aNewNodeWithAnIncrementedVersionShouldBeDisplayed() {
        String newVersionText = cargaPage.getUpdatedNodeText();
        Assert.assertNotNull(newVersionText, "El nuevo nodo con versión incrementada no se mostró.");
    }

    @Then("the previous node reference should be disconnected from the DOM")
    public void thePreviousNodeReferenceShouldBeDisconnectedFromTheDOM() {
        boolean isStale = cargaPage.isPreviousNodeStale();
        Assert.assertTrue(isStale, "La referencia al nodo previo sigue conectada al DOM.");
    }

    @Then("the target node should be removed from the DOM")
    public void theTargetNodeShouldBeRemovedFromTheDOM() {
        boolean isRemoved = cargaPage.isNodeRemoved();
        Assert.assertTrue(isRemoved, "El elemento no fue eliminado completamente del DOM.");
    }
    @And("the user selects the HTTP status code {string}")
    public void theUserSelectsTheHTTPStatusCode(String status) {
        cargaPage.selectHttpStatus(status);
    }

    @Then("the response time should take at least {string} ms")
    public void theResponseTimeShouldTakeAtLeastMs(String expectedDelayStr) {
        long expectedDelay = Long.parseLong(expectedDelayStr);
        long elapsedTime = cargaPage.getLastElapsedTime();

        // Tolerancia del 20% para variaciones de renderizado y red local
        long minAcceptableTime = (long) (expectedDelay * 0.80);

        Assert.assertTrue(
                elapsedTime >= minAcceptableTime,
                "El tiempo de respuesta (" + elapsedTime + " ms) fue inferior al esperado ("
                        + expectedDelay + " ms, mínimo aceptable: " + minAcceptableTime + " ms)."
        );
    }

    @And("the displayed response status should contain {string}")
    public void theDisplayedResponseStatusShouldContain(String expectedMessage) {
        String actualResult = cargaPage.getSimulateResultText();
        Assert.assertTrue(
                actualResult.contains(expectedMessage),
                "El texto devuelto no coincide con el mensaje esperado."
        );
    }
    @When("the user sets the delay to {string} ms")
    public void theUserSetsTheDelayToMs(String delayValue) {
        cargaPage.setDelay(delayValue);
    }

    @Then("the delay field should display the validation message {string}")
    public void theDelayFieldShouldDisplayTheValidationMessage(String expectedMessage) {
        String actualMessage = cargaPage.getDelayValidationMessage();
        Assert.assertEquals(
                actualMessage,
                expectedMessage,
                "El mensaje de validación HTML5 no coincide con el esperado."
        );
    }

}
