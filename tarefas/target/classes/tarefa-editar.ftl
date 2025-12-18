<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>
      Editar Tarefa
    </title>
    <link rel="stylesheet" href="/style.css">
  </head>
  <body>
    <div class="container">
      <h1>
        Editar Tarefa
      </h1>
      <a href="/logout">
        <button type="button" class="logout">
          Sair
        </button>
      </a>
      <form method="post" action="/tarefas-view/${tarefa.id}/editar" class="novo-projeto">
        <input type="text" name="titulo" placeholder="Descrição da tarefa" value="${tarefa.titulo}" required>
        <input type="text" name="descricao" value="${tarefa.descricao}">
        <input type="text" name="status" value="${tarefa.status}">
        <button type="submit">
          Salvar Alterações
        </button>
      </form>
      <a href="/tarefas-view">
        <button type="button" class="voltar">
          Voltar para Tarefas
        </button>
      </a>
    </div>
  </body>
</html>
