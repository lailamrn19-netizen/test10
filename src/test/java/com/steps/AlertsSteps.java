package com.steps;

import com.pages.AlertsPage;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class AlertsSteps {
    
    private AlertsPage alertsPage = new AlertsPage();
    
    @And("the user click on the button {string}")
    public void theUserClicksTheButton(String buttonName) {
        alertsPage.clickButton(buttonName);
    }


    @And("the user interacts with the dialog using action {string} and input {string}")
    public void theUserInteractsWithTheDialogUsingActionAndInput(String action, String inputText) {
        alertsPage.handleDialog(action, inputText);
    }

    @Then("the status message should display {string}")
    public void theStatusMessageShouldDisplay(String expectedMessage) {
        if (!expectedMessage.equalsIgnoreCase("N/A")) {
            String actualMessage = alertsPage.getStatusMessage();
            Assert.assertEquals(actualMessage, expectedMessage, "El mensaje desplegado no coincide con el esperado.");
        }
    }
    @Then("a new tab should open with the path {string}")
    public void aNewTabShouldOpenWithThePath(String expectedPath) {
        alertsPage.switchToNewTab();
        Assert.assertTrue(alertsPage.isPathInUrl(expectedPath), "La URL de la nueva pestaña no contiene la ruta esperada: " + expectedPath);
    }

    @And("the user switches to the new reservation tab")
    public void theUserSwitchesToTheNewReservationTab() {
        alertsPage.switchToNewTab();
    }

    @When("the user fills the reservation form with experience {string}, date {string}, email {string}, and tickets {string}")
    public void theUserFillsTheReservationFormWithExperienceDateEmailAndTickets(String experience, String date, String email, String tickets) {
        alertsPage.fillReservationForm(experience, date, email, tickets);
    }


    @Then("the reservation status message should display {string}")
    public void theReservationStatusMessageShouldDisplay(String expectedMessage) {
        String actualMessage = alertsPage.getReservationStatusMessage();
        Assert.assertTrue(actualMessage.contains(expectedMessage), "El mensaje esperado no coincide. Se obtuvo: " + actualMessage);
    }

    @And("the original tab should remain accessible")
    public void theOriginalTabShouldRemainAccessible() {
        Assert.assertTrue(alertsPage.isOriginalTabAccessible(), "La pestaña original no está accesible.");
    }
    @When("the user requests to download the report in {string} format")
    public void theUserRequestsToDownloadTheReportInFormat(String format) {
        alertsPage.selectReportFormat(format);
        alertsPage.clickDownloadButton();
    }

    @Then("the downloaded file name should be {string}")
    public void theDownloadedFileNameShouldBe(String fileName) {
        boolean isDownloaded = alertsPage.isFileDownloaded(fileName);
        Assert.assertTrue(isDownloaded, "El archivo " + fileName + " no se descargó en el tiempo esperado.");
    }
    @And("the downloaded file content should contain {string}")
    public void theDownloadedFileContentShouldContain(String expectedContent) {
        // Obtenemos el nombre del archivo según el contexto o se verifica el contenido
        boolean contentValid = alertsPage.verifyFileContent("reporte-qa.txt", expectedContent)
                || alertsPage.verifyFileContent("reporte-qa.csv", expectedContent);
        Assert.assertTrue(contentValid, "El contenido del archivo no coincide con lo esperado: " + expectedContent);
    }

    @And("the page should display a notification message with {string}")
    public void thePageShouldDisplayANotificationMessageWith(String fileName) {
        String notification = alertsPage.getNotificationText();
        Assert.assertTrue(notification.contains(fileName),
                "El mensaje en pantalla no menciona el archivo " + fileName + ". Mensaje recibido: " + notification);
    }
    @When("the user uploads a file named {string} with size {string}")
    public void theUserUploadsAFileNamedWithSize(String fileName, String fileSize) {
        if (fileName.isEmpty()) {
            alertsPage.clickUploadButton();
        } else {
            long sizeInMB = Long.parseLong(fileSize.replaceAll("[^0-9]", ""));
            String filePath = alertsPage.createDummyFile(fileName, sizeInMB);
            alertsPage.uploadFile(filePath);
            alertsPage.clickUploadButton();
        }
    }

    @Then("the response status should be {string}")
    public void theResponseStatusShouldBe(String expectedResult) {
        Assert.assertEquals(alertsPage.GreetingText(), expectedResult);
    }

    @And("the response message should contain {string}")
    public void theResponseMessageShouldContain(String expectedMessage) {
        String message = alertsPage.getResponseMessage();
        Assert.assertTrue(message.toLowerCase().contains(expectedMessage.toLowerCase()),
                "El mensaje recibido [" + message + "] no contiene: " + expectedMessage);
    }

}
