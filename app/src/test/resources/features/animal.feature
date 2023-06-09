# language: pt

Funcionalidade: Gerenciamento de um animal da Loja

#  Cenario: Lista somente animais disponíveis para a venda
#    Quando o usuário pesquisa todos os animais available
#    Então o usuário recebe uma lista com 7 animais

#  Cenario: Lista somente animais pending
#    Dado que a loja possua animais pending
#    Quando o usuário pesquisa todos os animais pending
#    Então o usuário recebe uma lista com 2 animais

#  Cenario: Lista somente animais sold
#    Dado que a loja não possua animais sold
#    Quando o usuário pesquisa todos os animais sold
#    Então o usuário recebe uma lista com 0 animal

  Esquema do Cenario: Lista animais pelo seu status de venda
    Dado que a loja não possua animais sold
    Quando o usuário pesquisa todos os animais <status>
    Então o usuário recebe uma lista com <quantidade> animal

    Exemplos: Animais em estoque
      | status    | quantidade |
      | available | 7          |
      | pending   | 2          |
      | sold      | 0          |

  Cenario: Lista animais disponíveis para a venda
    Dado que a loja possua animais available
    Quando o usuário pesquisa por todos os animais available
    Então recebe a lista com 7 animais available
    E 3 animais possuem o nome Lion