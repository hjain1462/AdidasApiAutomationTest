package com.adidas.bdd.api.request;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.UUID;

import com.adidas.bdd.api.common.FileLocations;
import com.adidas.bdd.api.constants.Context;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.apache.http.params.CoreConnectionPNames;

public class RequestSender {

    protected static ValidatableResponse response;
    protected static RequestSpecification request;


    private RestAssuredConfig connectionTimeOutConfig() {
        return RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000));
    }

    public RequestSender() {
        request = RestAssured.given().config(connectionTimeOutConfig());
        request = request.log().all();

        request.baseUri(Optional.ofNullable(System.getenv("URI")).orElse(Context.baseUrl));
        request.header("X-Correlation-ID", UUID.randomUUID());
        request.accept("application/json");
    }


    protected String getRequestBodyFromJsonFile(String jsonFileName) {
        File jsonPathLocation = new File(FileLocations.jsonPath, jsonFileName);
        FileInputStream fileInputStream;
        String requestBody = null;
        try {
            fileInputStream = new FileInputStream(new File(jsonPathLocation.getAbsolutePath()));
            requestBody = IOUtils.toString(fileInputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestBody;

    }

    protected void setAuthorizationHeader(String accessToken) {
        request.header("Authorization", "Bearer " + accessToken);
    }

    protected void setRequestBody(String body) {
        request.body(body);
    }

    protected void setBasicUserNameAndPassword(String clientId, String clientPassword) {
        request.auth().preemptive().basic(clientId, clientPassword);
    }

    protected static void setEndPoint(String endPoint) {
        request.basePath(endPoint);
    }
    protected void setContentType(String contentType) {
        request.contentType(contentType);
    }

    protected static ValidatableResponse sendPostRequest() {
        return request.when().post().then().log().all();
    }

    protected ValidatableResponse sendGetRequest() {
        return request.when().get().then().log().all();
    }

    protected ValidatableResponse sendPutRequest() {
        return request.when().put().then().log().all();
    }

    protected ValidatableResponse sendDeleteRequest() {
        return request.when().delete().then().log().all();
    }

    protected ValidatableResponse sendPatchRequest() {
        return request.when().patch().then().log().all();
    }

    protected void setGrantTypeAndScopeInQueryParam(String grantType, String scope) {
        request.queryParam("grantType", grantType);
        request.queryParam("scope", scope);
    }

}