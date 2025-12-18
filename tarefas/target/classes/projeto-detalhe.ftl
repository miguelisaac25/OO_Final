<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>
      Projeto ${projeto.nome}
    </title>
    <link rel="stylesheet" href="/style.css">
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
      <h2>
        Tarefas
      </h2>
      <form method="post" action="/projetos-view/${projeto.id}/tarefas" class="novo-projeto">
        <input type="text" name="titulo" placeholder="Descrição da tarefa" required>
        <button type="submit">
          Adicionar Tarefa
        </button>
      </form>
      <h2>
        Tarefas
      </h2>
      <ul style="list-style: none; padding: 0;">
        <#list tarefas as t>
          <li style="margin-bottom: 10px; display: flex; align-items: center;">
            <input type="checkbox" disabled <#if t.status == "Concluida">
            checked
          </#if>
          style="margin-right: 10px;">
          <#if t.status == "Concluida">
            <s>
              ${t.titulo}
            </s>
          <#else>
            ${t.titulo}
          </#if>
          <div style="margin-left: auto;" class="acoes">
            <form style="display:inline" method="post" action="/projetos-view/${projeto.id}/tarefas/${t.id}/toggle">
              <button type="submit" style="margin-left: 10px;">
                <#if t.status == "Concluida">
                  Reabrir
                <#else>
                  Concluir
                </#if>
              </button>
            </form>
            <a href="/projetos-view/${projeto.id}/tarefas/${t.id}/editar" style="margin-left: 10px;">
              <button type="button" class="editar">
                Editar
              </button>
            </a>
            <form style="display:inline" method="post" action="/projetos-view/${projeto.id}/tarefas/${t.id}/delete" style="margin-left: 10px;">
              <button type="submit" class="deletar">
                Excluir
              </button>
            </form>
          </div>
        </li>
      </#list>
    </ul>
    <a href="/projetos-view/">
      <button type="button" class="voltar">
        Voltar para Projetos
      </button>
    </a>
  </div>
</body>
</html>
