package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.api.StoreApi;
import bdd.automation.api.support.domain.Order;
import bdd.automation.api.support.domain.Pet;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class StoreStepDefinitions {
    private final PetApi petApi;
    private final StoreApi storeApi;
    private Pet expectedPet;
    private Order expectedOrder;
    private Map<String, Integer> expectedInventory;

    public StoreStepDefinitions() {
        petApi = new PetApi();
        storeApi = new StoreApi();
    }

    @Dado("que a loja possua animais com o status {word}")
    public void queALojaPossuaAnimaisComOStatusAvailable(String status) {
        Pet pet = Pet.builder().id(77).status(status).build();

        expectedPet = petApi.createPet(pet);
    }

    @Quando("o usuário faz um pedido deste animal")
    public void oUsuarioFazUmPedidoDesteAnimal() {
        Order order = Order.builder().petId(expectedPet.getId()).build();

        expectedOrder = storeApi.createOrder(order);
    }

    @Entao("o pedido deve ser aprovado")
    public void oPedidoDeveSerAprovado() {
        Response actualOrderResponse = storeApi.getOrder(expectedOrder.getId());

        actualOrderResponse.
                then().
                body("id", is(expectedOrder.getId()),
                        "petId", is(expectedPet.getId()),
                        "quantity", is(expectedOrder.getQuantity()),
                        "status", is(expectedOrder.getStatus())
                );
    }

    @Quando("o usuário puxa os status dos serviços")
    public void oUsuarioPuxaOsStatusDosServicos() {
        expectedInventory = storeApi.getInventory();

        //map.get("approved")
    }

    @Entao("deve existir {int} de cada {word}")
    public void deveExistirQuantidadeDeCadaStatus(int quantity, String status) {
        assertThat(expectedInventory.get(status), is(quantity));
    }
}
