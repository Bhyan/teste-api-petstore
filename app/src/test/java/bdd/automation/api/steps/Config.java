package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.api.UserApi;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class Config {

    private final UserApi userApi;
    private final PetApi petApi;

    public Config() {
        userApi = new UserApi();
        petApi = new PetApi();
    }

    @Before
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/api";

        RestAssured.requestSpecification = new RequestSpecBuilder().
            setContentType(ContentType.JSON).
            build();
    }

    @After("@deleteAllUsers")
    public void deleteUsers() {
        userApi.deleteAllUsers();
    }

    @After("@DeleteExtraPets")
    public void deleteExtraPets() {
        petApi.deleteExtraPets("available");
    }
}
