package com.sistema.controller;

import java.util.HashMap;
import java.util.Map;

import com.sistema.dao.UsuarioDAO;
import com.sistema.model.Usuario;

import io.javalin.Javalin;

public class UsuarioController {

    private final UsuarioDAO dao = new UsuarioDAO();

    public void registrar(Javalin app) {

        app.get("/login-view", ctx -> {
            ctx.render("login.ftl");
        });

        app.get("/register-view", ctx -> {
            ctx.render("register.ftl");
        });

        app.post("/login", ctx -> {
            String email = ctx.formParam("email");
            String senha = ctx.formParam("senha");

            Usuario usuario = dao.buscarPorEmail(email);

            if (usuario != null && usuario.getSenha().equals(senha)) {
                ctx.sessionAttribute("usuarioId", usuario.getId());
                ctx.sessionAttribute("usuarioNome", usuario.getEmail());
                ctx.redirect("/projetos-view");
            } else {
                Map<String, Object> model = new HashMap<>();
                model.put("erro", "Email ou senha incorretos");
                ctx.render("login.ftl", model);
            }
        });

        app.post("/register", ctx -> {
            Usuario usuario = new Usuario();
            usuario.setEmail(ctx.formParam("email"));
            usuario.setSenha(ctx.formParam("senha"));

            dao.inserir(usuario);

            ctx.redirect("/login-view");
        });

        app.get("/logout", ctx -> {
            ctx.sessionAttribute("usuarioId", null);
            ctx.sessionAttribute("usuarioNome", null);
            ctx.redirect("/login-view");
        });
    }
}
