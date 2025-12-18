<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>
      Tarefas Gerais
    </title>
    <link rel="stylesheet" href="/style.css">
  </head>
  <body>
    <div class="container">
      <h1>
        Todas as Minhas Tarefas
      </h1>
      <a href="/logout">
        <button type="button" class="logout">
          Sair
        </button>
      </a>
      <table>
        <tr>
          <th>
            Tarefa
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
              ${t.titulo}
            </td>
            <td>
              ${t.status}
            </td>
            <td class="acoes">
              <a href="/tarefas-view/${t.id}/editar">
                <button class="editar">
                  Editar
                </button>
              </a>
            </td>
          </tr>
        </#list>
      </table>
      <br>
      <a href="/projetos-view">
        <button class="voltar">
          Voltar para Projetos
        </button>
      </a>
    </div>
  </body>
</html>
