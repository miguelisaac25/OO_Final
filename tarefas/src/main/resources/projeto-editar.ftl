<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>
      Editar Projeto
    </title>
    <link rel="stylesheet" href="/style.css">
  </head>
  <body>
    <div class="container">
      <h1>
        Editar Projeto
      </h1>
      <a href="/logout">
        <button type="button" class="logout">
          Sair
        </button>
      </a>
      <form method="post" action="/projetos-view/${projeto.id}/editar" class="novo-projeto">
        <input type="text" name="nome" placeholder="Nome do projeto" value="${projeto.nome}" required>
        <input type="text" name="descricao" placeholder="Descrição do projeto" value="${projeto.descricao}" required>
        <button type="submit">
          Salvar Alterações
        </button>
      </form>
      <a href="/projetos-view">
        <button type="button" class="voltar">
          Voltar para Projetos
        </button>
      </a>
    </div>
  </body>
</html>
