<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8">
    <title>
      Editar Tarefa | Gestão de Projetos
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
      <div class="edit-box">
        <form method="post" action="/tarefas-view/${tarefa.id}/editar">
          <div style="width: 100%; display: flex; flex-direction: column; gap: 15px;">
            <label for="titulo">
              Título da Tarefa:
            </label>
            <input type="text" id="titulo" name="titulo" 
                           placeholder="Ex: Finalizar relatório" 
                           value="${tarefa.titulo}" required>
            <label for="descricao">
              Descrição:
            </label>
            <input type="text" id="descricao" name="descricao" 
                           placeholder="Detalhes da tarefa..." 
                           value="${tarefa.descricao!''}">
            <button type="submit" style="margin-top: 10px;">
              Salvar Alterações
            </button>
          </div>
        </form>
      </div>
      <hr style="margin: 20px 0; border: 0; border-top: 1px solid #eee;">
      <#-- Volta para o projeto se houver ID, caso contrário volta para tarefas gerais -->
      <a href="${(tarefa.projetoId gt 0)?string('/projetos-view/' + tarefa.projetoId, '/tarefas-view')}">
        <button type="button" class="voltar">
          Cancelar e Voltar
        </button>
      </a>
    </div>
  </body>
</html>
