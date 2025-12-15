<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Tarefa</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
<div class="container">
    <h1>Editar Tarefa</h1>
    <a href="/logout"><button type="button" class="logout">Sair</button></a>

    <form method="post" action="/projetos-view/${projeto.id}/tarefas/${tarefa.id}/editar" class="novo-projeto">
        <input type="text" name="titulo" placeholder="Descrição da tarefa" value="${tarefa.titulo}" required>
        <button type="submit">Salvar Alterações</button>
    </form>

    <a href="/projetos-view/${projeto.id}"><button type="button" class="voltar">Voltar para Projeto</button></a>
</div>
</body>
</html>