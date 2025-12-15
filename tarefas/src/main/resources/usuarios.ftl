<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuários</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>

<h1>Usuários</h1>
<a href="/logout"><button type="button" class="logout">Sair</button></a>

<form method="post" action="/usuarios-view">
    <input type="text" name="nome" placeholder="Nome" required>
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="senha" placeholder="Senha" required>
    <button type="submit">Salvar</button>
</form>

<hr>

<table>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
    </tr>

    <#list usuarios as u>
        <tr>
            <td>${u.id}</td>
            <td>${u.nome}</td>
            <td>${u.email}</td>
        </tr>
    </#list>
</table>
