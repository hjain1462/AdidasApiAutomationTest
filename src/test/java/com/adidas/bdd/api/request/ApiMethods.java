package com.adidas.bdd.api.request;

import java.util.Map;

import com.adidas.bdd.api.constants.EndpointCreator;
import io.restassured.response.ValidatableResponse;

import static com.adidas.bdd.steps.RestCallSteps.petId;

public class ApiMethods extends RequestSender {

    public ApiMethods(){
        super();
    }


    public ValidatableResponse updateExistingPet(String fileName, Map<String, String> replacement){
        String requestBody = getRequestBodyFromJsonFile(fileName);
        for (Map.Entry<String, String> map : replacement.entrySet()) {
            requestBody = requestBody.replace(map.getKey(), map.getValue());
        }
        setContentType("application/json");
        setRequestBody(requestBody);
        setEndPoint(EndpointCreator.PET_STORE_ENDPOINT);
        return sendPutRequest();
    }

    public ValidatableResponse addNewPet(String fileName, Map<String,String> replacement) {
        String requestBody = getRequestBodyFromJsonFile(fileName);
        for (Map.Entry<String, String> map : replacement.entrySet()) {
            requestBody = requestBody.replace(map.getKey(), map.getValue());
        }
        setContentType("application/json");
        setRequestBody(requestBody);
        setEndPoint(EndpointCreator.PET_STORE_ENDPOINT);
        return sendPostRequest();
    }

    public ValidatableResponse getAllPetDetails() {
        setContentType("application/json");
        setEndPoint(EndpointCreator.GET_AVAILABLE_PETS);
        return sendGetRequest();
    }

    public ValidatableResponse deleteAddedPet() {
        setEndPoint(EndpointCreator.deletePetDetails(petId));
       return sendDeleteRequest();
    }

    public ValidatableResponse getPetDetailsById() {
        setEndPoint(EndpointCreator.getPetDetailsById(petId));
       return sendGetRequest();
    }

}
