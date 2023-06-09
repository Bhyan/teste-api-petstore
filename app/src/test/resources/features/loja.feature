# language: pt

  Funcionalidade: Gerenciamento de pedidos

    @DeleteExtraPets
    Cenario: Cliente cria um pedido na loja
      Dado que a loja possua animais com o status available
      Quando o usuário faz um pedido deste animal
      Então o pedido deve ser aprovado