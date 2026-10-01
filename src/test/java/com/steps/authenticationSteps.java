package com.steps;

import com.pages.authenticationPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class authenticationSteps {
    private final authenticationPage authenticationPage = new authenticationPage();
    @When("the user selects the {string} from the store main page")
    public void selectStoreCategoryCard(String categoryName) {
        authenticationPage.selectStoreCategoryCard(categoryName);
    }
    @And("the user enters username {string} and password {string}")
    public void theUserEntersLoginCredentialsAnd(String userName, String password) {
        authenticationPage.fillLoginForm(userName, password);
    }
    @And("the user clicks the login button")
    public void clickButtonByName() {
        authenticationPage.clickButtonByName();
    }
    @Then("an immediate error message {string} should be displayed")
    public void anErrorMessageShouldBeDisplayed(String expectedError) {
        Assert.assertEquals(authenticationPage.getErrorMessage(), expectedError);
    }
    @Then("a success message {string} should be displayed")
    public void aSuccessMessageShouldBeDisplayed(String expectedMessage) {
        String actualMessage = authenticationPage.getLoginResultMessage(expectedMessage);
        Assert.assertEquals(actualMessage, expectedMessage);
    }
    @Then("an auth error message {string} should be displayed")
    public void anAuthErrorMessageShouldBeDisplayed(String expectedError) {
        String actualError = authenticationPage.getAuthErrorMessage(expectedError);
        Assert.assertEquals(actualError, expectedError, "El mensaje de error devuelto no coincide");
    }

}
