# language: pt

Funcionalidade: Gerenciamento de pedidos

  @DeleteExtraPets @DeleteExtraOrders
  Cenario: Cliente cria um pedido na loja
    Dado que a loja possua animais com o status available
    Quando o usuário faz um pedido deste animal
    Então o pedido deve ser aprovado

  Cenario: Deletar um pedido
    Dado que exista um pedido cadastrado no sistema
    Quando o usuário deletar um pedido
    Entao o pedido não deve esta disponível no sistema

  Esquema do Cenario: Validar inventário da loja
    Quando o usuário puxa os status dos serviços
    Então deve existir <quantidade> de cada <status>

    Exemplos: Status do serviço
      | status    | quantidade |
      | approved  | 50         |
      | placed    | 100        |
      | delivered | 50         |