 # language: pt
 Funcionalidade: Gerenciamento de um usuario da PetSore

   @deleteAllUsers
   Cenario: Cria usuário na loja refletindo o negócio
     Quando crio um usuário
     Então o usuário é salvo no sistema

   Cenario: Criar usuário em lote
     Quando crio varios usuários
     Então os usuários são salvos no sistema

   Cenario: Editar um usuário
     Dado que a loja possua usuário cadastrado
     Quando o usuário é editado
     Então o usuário deve esta disponível no sistema com os novos dados

   Cenario: Deletar um usuário
     Dado que a loja possua usuário cadastrado
     Quando o usuário é deletado
     Então o usuário não deve esta disponível no sistema