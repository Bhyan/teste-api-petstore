# language: pt

Funcionalidade: Gerenciamento de um animal da Loja

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

  Cenario: Criar um animal
    Quando o usuário cadastrar um novo animal
    Então o animal deve esta disponível no sistema

  Cenario: Editar um animal
    Dado que a loja possua animal cadastrado
    Quando o usuário edita o animal
    Então o animal deve esta disponível no sistema

  Cenario: Deletar um animal
    Dado que a loja possua animal cadastrado
    Quando o usuário deletar o animal
    Então o animal não deve esta disponível no sistema

  Esquema do Cenario: Lista animais pelo sua tag
    Quando o usuário pesquisa todos os animais com a <tag>
    Então o usuário recebe uma lista com <quantidade> animal

    Exemplos: Animais em estoque
      | tag  | quantidade |
      | tag1 | 3          |
      | tag2 | 5          |
      | tag3 | 6          |
      | tag4 | 4          |