package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.domain.Pet;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetsStepDefinitions {

    private final PetApi petApi;
    private List<Pet> actualPets;
    private Response actualPetsResponse;

    public PetsStepDefinitions() {
        petApi = new PetApi();
    }

    @Dado("que a loja possua animais available")
    public void queALojaPossuaAnimaisAvailable() {
    }

    @Quando("o usuário pesquisa todos os animais {word}")
    public void oUsuárioPesquisaTodosOsAnimaisDisponíveis(String status) {
        actualPets = petApi.getPetsByStatus(status);
    }

    @Então("o usuário recebe uma lista com {int} animal/animais")
    public void oUsuárioRecebeUmaListaComAnimais(int quantity) {
        assertThat(actualPets.size(), is(quantity));
    }

    @Dado("que a loja não possua animais {word}")
    public void queALojaNãoPossuaAnimaisSold(String status) {
        petApi.deletePetsByStatus(status);
    }

    @Quando("o usuário pesquisa por todos os animais {word}")
    public void iDoASearchForAllPetsAvailable(String status) {
        actualPetsResponse = petApi.getPetsResponseByStatus(status);
    }

    @Então("recebe a lista com {int} animais {word}")
    public void iReceiveAListOfPetsAvailable(int petsQuantity, String status) {
        actualPetsResponse.
                then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "size()", is(petsQuantity),
                        "findAll { it.status == '" + status + "' }.size()", is(petsQuantity)
                );

    }

    @E("{int} animais possuem o nome {word}")
    public void petsHasTheNameLion(int petsQuantity, String petName) {
        actualPetsResponse.
                then().
                body(
                        "findAll { it.name.contains('"+petName+"') }.size()", is(petsQuantity)
                );
    }
}
