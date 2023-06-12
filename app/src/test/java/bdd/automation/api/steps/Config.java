package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.api.StoreApi;
import bdd.automation.api.support.api.UserApi;
import bdd.automation.api.support.domain.Order;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class Config {

    private final UserApi userApi;
    private final PetApi petApi;
    private final StoreApi storeApi;

    public Config() {
        userApi = new UserApi();
        petApi = new PetApi();
        storeApi = new StoreApi();
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

    @After("@DeleteExtraOrders")
    public void deleteExtraOrders() {
        storeApi.deleteOrder(Order.builder().build().getId());
    }
}
