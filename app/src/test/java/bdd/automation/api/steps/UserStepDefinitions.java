package bdd.automation.api.steps;

import bdd.automation.api.support.api.UserApi;
import bdd.automation.api.support.domain.User;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserStepDefinitions {

    private User expectedUser;
    private List<User> expectedUsers;
    private final UserApi userApi;

    public UserStepDefinitions() {
        userApi = new UserApi();
    }

    @Quando("crio um usuário")
    @Dado("que a loja possua usuário cadastrado")
    public void createAUser() {
        expectedUser = User.builder().build();

        userApi.createUser(expectedUser);
    }

    @Entao("o usuário é salvo no sistema")
    public void theCreatedUserWasStored() {
        String actualUsername = userApi.getUsername(expectedUser);

        assertThat(actualUsername, is(expectedUser.getUsername()));
    }

    @Quando("crio varios usuários")
    public void crioVariosUsuarios() {
        expectedUsers = Arrays.asList(User.builder().build(),
                User.builder().id(70).username("jao").firstName("João").lastName("Silva").email("jao@jj.com").build());

        userApi.createUsers(expectedUsers);
    }

    @Entao("os usuários são salvos no sistema")
    public void osUsuariosSaoSalvosNoSistema() {
        String actualUsername = userApi.getUsername(expectedUsers.get(0));
        assertThat(actualUsername, is(expectedUsers.get(0).getUsername()));

        actualUsername = userApi.getUsername(expectedUsers.get(1));
        assertThat(actualUsername, is(expectedUsers.get(1).getUsername()));
    }

    @Quando("o usuário é editado")
    public void oUsuarioEEditado() {
        expectedUser = User.builder().username("jao").build();

        userApi.editUser(expectedUser);
    }

    @Entao("o usuário deve esta disponível no sistema com os novos dados")
    public void oUsuarioDeveEstaDisponivelNoSistemaComOsNovosDados() {
        String actualUsername = userApi.getUsername(expectedUser);

        assertThat(actualUsername, is(expectedUser.getUsername()));
    }

    @Quando("o usuário é deletado")
    public void oUsuarioEDeletado() {
        userApi.deleteUser(expectedUser);
    }

    @Entao("o usuário não deve esta disponível no sistema")
    public void oUsuarioNaoDeveEstaDisponivelNoSistema() {
        Response expectedResponse = userApi.getUserResponseByUsername(expectedUser);

        expectedResponse.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }
}
