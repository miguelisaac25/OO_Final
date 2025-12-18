<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>
      Tarefas
    </title>
    <link rel="stylesheet" href="/style.css">
  </head>
  <body>
    <h1>
      Tarefas
    </h1>
    <a href="/logout">
      <button type="button" class="logout">
        Sair
      </button>
    </a>
    <form method="post" action="/tarefas-view">
      <input type="text" name="titulo" placeholder="Título da tarefa" required>
      <input type="text" name="descricao" placeholder="Descrição" required>
      <input type="text" name="status" placeholder="Status" required>
      <button type="submit">
        Adicionar Tarefa
      </button>
    </form>
    <hr>
    <table>
      <tr>
        <th>
          ID
        </th>
        <th>
          Título
        </th>
        <th>
          Descrição
        </th>
        <th>
          Status
        </th>
        <th>
          Ações
        </th>
      </tr>
      <#list tarefas as t>
        <tr>
          <td>
            ${t.id}
          </td>
          <td>
            ${t.titulo}
          </td>
          <td>
            ${t.descricao}
          </td>
          <td>
            ${t.status}
          </td>
          <td>
            <a href="/tarefas-view/${t.id}/editar">
              <button type="button">
                Editar
              </button>
            </a>
            <form method="post" action="/tarefas-view/${t.id}/delete" style="display:inline">
              <button type="submit">
                Excluir
              </button>
            </form>
          </td>
        </tr>
      </#list>
    </table>
  </body>
</html>
