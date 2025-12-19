<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>
      Projeto ${projeto.nome}
    </title>
    <link rel="stylesheet" href="/style.css">
    <style>
      .acoes a {
      text-decoration: none !important;
      display: inline-block;
      vertical-align: middle;
      }
      .acoes form {
      display: inline-block;
      margin: 0;
      vertical-align: middle;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>
        ${projeto.nome}
      </h1>
      <a href="/logout">
        <button type="button" class="logout">
          Sair
        </button>
      </a>
      <p>
        ${projeto.descricao}
      </p>
      <form method="post" action="/projetos-view/${projeto.id}/delete" style="margin-bottom:20px;">
        <button class="deletar" type="submit">
          Deletar Projeto
        </button>
      </form>
      <hr>
      <h2>
        Tarefas
      </h2>
      <form method="post" action="/projetos-view/${projeto.id}/tarefas" class="novo-projeto">
        <input type="text" name="titulo" placeholder="Título da tarefa" required>
        <input type="text" name="descricao" placeholder="Descrição">
        <button type="submit">
          Adicionar Tarefa
        </button>
      </form>
      <ul style="list-style: none; padding: 0;">
        <#list tarefas as t>
          <li style="margin-bottom: 10px; display: flex; align-items: center; background: #f9f9f9; padding: 10px; border-radius: 5px; box-shadow: 0 1px 3px rgba(0,0,0,0.1);">
            <form method="post" action="/tarefas-view/${t.id}/toggle" style="margin-right: 15px;">
              <button type="submit" style="background: none; border: none; cursor: pointer; font-size: 20px; padding: 0;">
                <#if t.status == "Concluida">
                  ✅
                <#else>
                  ⭕
                </#if>
              </button>
            </form>
            <div style="flex-grow: 1;">
              <#if t.status == "Concluida">
                <s style="color: #888;">
                  ${t.titulo}
                </s>
              <#else>
                <strong>
                  ${t.titulo}
                </strong>
              </#if>
              <br>
              <small style="color: #666;">
                ${t.descricao!""}
              </small>
            </div>
            <div class="acoes">
              <a href="/tarefas-view/${t.id}/editar" class="btn-link">
                <button type="button" class="editar">
                  Editar
                </button>
              </a>
              <form method="post" action="/tarefas-view/${t.id}/delete">
                <button type="submit" class="deletar">
                  Excluir
                </button>
              </form>
            </div>
          </li>
        </#list>
      </ul>
      <a href="/projetos-view">
        <button type="button" class="voltar">
          Voltar para Projetos
        </button>
      </a>
    </div>
  </body>
</html>
