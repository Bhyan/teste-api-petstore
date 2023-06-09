package bdd.automation.api.steps;

import bdd.automation.api.support.api.PetApi;
import bdd.automation.api.support.api.StoreApi;
import bdd.automation.api.support.domain.Order;
import bdd.automation.api.support.domain.Pet;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;

import static org.hamcrest.CoreMatchers.is;

public class StoreStepDefinitions {
    PetApi petApi;
    StoreApi storeApi;
    Pet expectedPet;
    Order expectedOrder;

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
    public void oUsuárioFazUmPedidoDesteAnimal() {
        Order order = Order.builder().petId(expectedPet.getId()).build();

        expectedOrder = storeApi.createOrder(order);
    }

    @Então("o pedido deve ser aprovado")
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
}
