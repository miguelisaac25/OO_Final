<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #2575fc, #6a11cb);
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        .register-container {
            background-color: #fff;
            padding: 40px 30px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.2);
            width: 350px;
            text-align: center;
        }

        .register-container h1 {
            margin-bottom: 30px;
            color: #333;
        }

        .register-container input {
            width: 100%;
            padding: 12px;
            margin: 10px 0;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 14px;
        }

        .register-container button {
            width: 100%;
            padding: 12px;
            background-color: #6a11cb;
            color: white;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            margin-top: 15px;
        }

        .register-container button:hover {
            background-color: #5710a3;
        }

        .register-container p {
            margin-top: 15px;
            font-size: 13px;
        }
    </style>
</head>
<body>
    <div class="register-container">
        <h1>Criar Conta</h1>

        <form method="post" action="/register">
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="senha" placeholder="Senha" required>
            <button type="submit">Cadastrar</button>
        </form>

        <p>
            Já tem conta?
            <a href="/login-view">Entrar</a>
        </p>
    </div>
</body>
</html>
