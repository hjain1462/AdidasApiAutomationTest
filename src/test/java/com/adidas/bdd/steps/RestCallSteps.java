package com.adidas.bdd.steps;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.adidas.bdd.api.common.ScenarioContext;
import com.adidas.bdd.api.request.ApiMethods;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import static com.adidas.bdd.api.constants.ResponseVariable.STATUS;

public class RestCallSteps extends ApiMethods {

    public static String petId;
    public static ApiMethods apiMethods = new ApiMethods();

    public RestCallSteps() {
        super();
    }

    @Before
    public void addCorrelationIdWithScenario(Scenario scenario) {
        ScenarioContext.INSTANCE.set("correlationId", ScenarioContext.correlationId);
        Logger log = Logger.getLogger(String.valueOf(ScenarioContext.class));
        log.info(scenario.getName() + "executed with correlationId" + ScenarioContext.correlationId);
    }

    @When("get all the available pets")
    public void getAllAvailablePets() {
        response = apiMethods.getAllPetDetails();
    }

    @Given("query params are:")
    public void addQueryParams(Map<String, String> queryParam) {
        request.queryParams("status", queryParam.get("status"));
    }

    @Then("check for available pets")
    public void assertAvailablePets() {
        JsonPath jsonPathEvaluator = response.extract().response().jsonPath();
        List<String> availableStatus = new ArrayList<>(jsonPathEvaluator.get(STATUS));
        int size = availableStatus.size();
        Assert.assertTrue(size > 0);
    }

    @Then("status code should be {int}")
    public void checkStatusCode(int statusCode) {
        response.statusCode(statusCode);
    }

    @When("add a new pet to store with template {string}")
    public void addNewPetToStore(String fileName, Map<String, String> replacement) {
        response = new ApiMethods().addNewPet(fileName, replacement);
    }

    @And("new pat is added to store")
    public void verifyNewPet() {
        JsonPath jsonPathEvaluator = response.extract().response().jsonPath();
        petId = jsonPathEvaluator.get("id").toString();
        Assert.assertEquals("123456", jsonPathEvaluator.get("id").toString());
    }

    @When("update status of newly added pet to sold with template {string}")
    public void updatePetStatus(String fileName, Map<String, String> replacement) {
        response = new ApiMethods().updateExistingPet(fileName, replacement);
    }

    @And("now the status is sold of newly added pet")
    public void checkPetStatus() {
        JsonPath jsonPathEvaluator = response.extract().response().jsonPath();
        Assert.assertEquals("sold", jsonPathEvaluator.get(STATUS));
    }

    @And("delete the newly added pet")
    public void deleteAddedPetFromStore() {
        response = new ApiMethods().deleteAddedPet();
    }

    @And("pet is removed from the store")
    public void validatePetRemovedOrNot() {
        response = new ApiMethods().getPetDetailsById();
        JsonPath jsonPathEvaluator = response.extract().response().jsonPath();
        Assert.assertEquals("Pet not found", jsonPathEvaluator.get("message"));
    }


}