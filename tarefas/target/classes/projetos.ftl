<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Meus Projetos</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
<div class="container">
    <h1>Meus Projetos</h1>
    <a href="/logout"><button type="button" class="logout">Sair</button></a>


    <form class="novo-projeto" method="post" action="/projetos-view">
        <input type="text" name="nome" placeholder="Nome do projeto" required>
        <input type="text" name="descricao" placeholder="Descrição do projeto" required>
        <button type="submit">Criar Projeto</button>
    </form>

    <table>
        <tr>
            <th>Nome</th>
            <th>Descrição</th>
            <th>Ações</th>
        </tr>
        <#list projetos as p>
        <tr>
            <td>${p.nome}</td>
            <td>${p.descricao}</td>
            <td class="acoes">
                <form style="display:inline" method="get" action="/projetos-view/${p.id}">
                    <button class="detalhes" type="submit">Detalhes</button>
                </form>
                <form style="display:inline" method="get" action="/projetos-view/${p.id}/editar">
                    <button class="editar" type="submit">Editar</button>
                </form>
                <form style="display:inline" method="post" action="/projetos-view/${p.id}/delete">
                    <button class="deletar" type="submit">Deletar</button>
                </form>
            </td>
        </tr>
        </#list>
    </table>
</div>
</body>
</html>
