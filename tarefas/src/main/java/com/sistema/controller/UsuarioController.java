package com.sistema.controller;

import java.util.HashMap;
import java.util.Map;

import com.sistema.model.Usuario;
import com.sistema.repository.UsuarioRepository;
import io.javalin.Javalin;

public class UsuarioController {

    private UsuarioRepository repository = new UsuarioRepository();

    public void registrar(Javalin app) {

        app.post("/usuarios", ctx -> {
            Usuario usuario = ctx.bodyAsClass(Usuario.class);
            repository.salvar(usuario);
            ctx.status(201).result("Usuário criado com sucesso");
        });

        app.get("/usuarios", ctx -> ctx.json(repository.listar()));

        app.get("/usuarios/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Usuario usuario = repository.buscarPorId(id);
            if (usuario == null) {
                ctx.status(404).result("Usuário não encontrado");
            } else {
                ctx.json(usuario);
            }
        });

        app.put("/usuarios/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Usuario usuario = ctx.bodyAsClass(Usuario.class);
            usuario.setId(id);
            repository.salvar(usuario);
            ctx.result("Usuário atualizado com sucesso");
        });

        app.delete("/usuarios/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repository.deletar(id);
            ctx.result("Usuário removido com sucesso");
        });

        app.get("/usuarios-view", ctx -> {
            Map<String, Object> model = new HashMap<>();
            model.put("usuarios", repository.listar());
            ctx.render("usuarios.ftl", model);
        });

        app.post("/usuarios-view", ctx -> {
            Usuario usuario = new Usuario();
            usuario.setNome(ctx.formParam("nome"));
            usuario.setEmail(ctx.formParam("email"));
            usuario.setSenha(ctx.formParam("senha"));
            repository.salvar(usuario);
            ctx.redirect("/usuarios-view");
        });

        app.get("/login-view", ctx -> ctx.render("login.ftl"));

        app.post("/login", ctx -> {
            String email = ctx.formParam("email");
            String senha = ctx.formParam("senha");

            Usuario usuario = repository.buscarPorEmail(email);

            if (usuario != null && usuario.getSenha().equals(senha)) {
                ctx.sessionAttribute("usuarioId", usuario.getId());
                ctx.redirect("/projetos-view");
            } else {
                Map<String, Object> model = new HashMap<>();
                model.put("erro", "Email ou senha incorretos");
                ctx.render("login.ftl", model);
            }
        });

        app.get("/logout", ctx -> {
            ctx.sessionAttribute("usuarioId", null);
            ctx.redirect("/login-view");
        });
    }
}
