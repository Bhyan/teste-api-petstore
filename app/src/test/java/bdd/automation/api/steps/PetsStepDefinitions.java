package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.domain.Pet;

import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PetsStepDefinitions {

    private final PetApi petApi;
    private List<Pet> actualPets;
    private Pet expectedPet;
    private Response actualPetsResponse;

    public PetsStepDefinitions() {
        petApi = new PetApi();
    }

    @Dado("que a loja possua animais available")
    public void queALojaPossuaAnimaisAvailable() {
    }

    @Quando("o usuário pesquisa todos os animais {word}")
    public void oUsuarioPesquisaTodosOsAnimaisDisponíveis(String status) {
        actualPets = petApi.getPetsByStatus(status);
    }

    @Entao("o usuário recebe uma lista com {int} animal/animais")
    public void oUsuarioRecebeUmaListaComAnimais(int quantity) {
        assertThat(actualPets.size(), is(quantity));
    }

    @Dado("que a loja não possua animais {word}")
    public void queALojaNaoPossuaAnimaisSold(String status) {
        petApi.deletePetsByStatus(status);
    }

    @Quando("o usuário pesquisa por todos os animais {word}")
    public void oUsuarioPesquisaPorTodosAnimaisAvailable(String status) {
        actualPetsResponse = petApi.getPetsResponseByStatus(status);
    }

    @Entao("recebe a lista com {int} animais {word}")
    public void recebeAListaComAnimaisAvailable(int petsQuantity, String status) {
        actualPetsResponse.
                then().
                statusCode(HttpStatus.SC_OK).
                body(
                        "size()", is(petsQuantity),
                        "findAll { it.status == '" + status + "' }.size()", is(petsQuantity)
                );

    }

    @E("{int} animais possuem o nome {word}")
    public void animaisTemONomeLeao(int petsQuantity, String petName) {
        actualPetsResponse.
                then().
                body(
                        "findAll { it.name.contains('"+petName+"') }.size()", is(petsQuantity)
                );
    }

    @Quando("o usuário cadastrar um novo animal")
    public void oUsuarioCadastrarUmNovoAnimal() {
        Pet pet = Pet.builder().build();
        expectedPet = petApi.createPet(pet);
    }

    @Entao("o animal deve esta disponível no sistema")
    public void oAnimalEstaDisponivelNoSistema() {
        Pet pet = petApi.getPetById(expectedPet.getId());

        assertThat(pet.getName(), is(expectedPet.getName()));
        assertThat(pet.getStatus(), is(expectedPet.getStatus()));
    }

    @Dado("que a loja possua animal cadastrado")
    public void queALojaPossuaAnimalCadastrado() {
        Pet pet = Pet.builder().build();
        expectedPet = petApi.createPet(pet);
    }

    @Quando("o usuário edita o animal")
    public void oUsuarioEditaOAnimal() {
        Pet pet = Pet.builder().name("Chitara").status("pending").build();

        expectedPet = petApi.editPet(pet);
    }

    @Quando("o usuário deletar o animal")
    public void oUsuarioDeletarOAnimal() {
        petApi.deletePetById(expectedPet.getId());
    }

    @Entao("o animal não deve esta disponível no sistema")
    public void oAnimalNaoDeveEstaDisponivelNoSistema() {
        Response expectedResponse = petApi.getPetResponseById(expectedPet.getId());

        expectedResponse.then().statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Quando("o usuário pesquisa todos os animais com a {word}")
    public void oUsuarioPesquisaTodosOsAnimaisComATag(String tag) {
        actualPets = petApi.getPetByTag(tag);
    }
}
