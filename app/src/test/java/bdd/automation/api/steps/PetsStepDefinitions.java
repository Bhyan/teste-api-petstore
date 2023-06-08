package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.domain.Pet;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetsStepDefinitions {

    private final PetApi petApi;
    private List<Pet> actualPets;

    public PetsStepDefinitions() {
        petApi = new PetApi();
    }

    @Dado("que a loja possua animais {word}")
    public void queALojaPossuaAnimaisAvailable(String status) {
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
}
