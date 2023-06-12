package bdd.automation.api.support.api;

import bdd.automation.api.support.domain.Pet;
import bdd.automation.api.support.domain.User;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String CREATE_USER_ENDPOINT = "/v3/user";
    private static final String CREATE_USERS_ENDPOINT = "/v3/user/createWithList";
    private static final String USER_ENDPOINT = "/v3/user/{name}";

    public void createUser(User user) {
        given().
                body(user).
        when().
                post(CREATE_USER_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK);
    }
    public void createUsers(List<User> users) {
        given().
                body(users).
        when().
                post(CREATE_USERS_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK);
    }

    public void editUser(User user) {
        given().
                pathParam("name", user.getUsername()).
                body(user).
        when().
                put(USER_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK);
    }

    public String getUsername(User user) {
        return given().
                pathParam("name", user.getUsername()).
        when().
                get(USER_ENDPOINT).
        thenReturn().
                path("username");
    }

    public Response getUserResponseByUsername(User user) {
        return given().
                pathParam("name", user.getUsername()).
        when().
                get(USER_ENDPOINT);
    }

    public void deleteUser(User user) {
        given().
                pathParam("name", user.getUsername()).
        when().
                delete(USER_ENDPOINT).
        then().
                statusCode(HttpStatus.SC_OK);
    }

    public void deleteAllUsers() {
        List<User> usersList = List.of(User.builder().build());

        for(User user: usersList) {
            given().
                    pathParam("name", user.getUsername()).
            when().
                    delete(USER_ENDPOINT).
            then().
                    statusCode(HttpStatus.SC_OK);
        }
    }
}
