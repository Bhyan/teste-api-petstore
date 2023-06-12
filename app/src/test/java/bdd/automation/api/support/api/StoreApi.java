package bdd.automation.api.support.api;

import bdd.automation.api.support.domain.Order;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class StoreApi {

    private static final String CREATE_ORDER_ENDPOINT = "/v3/store/order";
    private static final String GET_INVENTORY_ENDPOINT = "/v3/store/inventory";
    private static final String ORDER_BY_ID_ENDPOINT = "/v3/store/order/{id}";

    public Order createOrder(Order order) {
        return given().
                body(order).
        when().
                post(CREATE_ORDER_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK).
                extract().body().as(Order.class);
    }

    public Response getOrder(int id) {
        return given().
                pathParam("id", id).
        when().
                get(ORDER_BY_ID_ENDPOINT);
    }

    public Map<String, Integer> getInventory() {
        return given().
        when().
            get(GET_INVENTORY_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK).
                extract().body().jsonPath().getMap("");
    }

    public void deleteOrder(int id) {
        given().
                pathParam("id", id).
        when().
                delete(ORDER_BY_ID_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK);
    }
}
