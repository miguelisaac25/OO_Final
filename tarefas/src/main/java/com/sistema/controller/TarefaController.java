package com.sistema.controller;

import java.util.HashMap;
import java.util.Map;

import com.sistema.dao.TarefaDAO;
import com.sistema.model.Tarefa;
import com.sistema.repository.TarefaRepository;

import io.javalin.Javalin;

public class TarefaController {

    private final TarefaDAO dao = new TarefaDAO();
    private final TarefaRepository repository = new TarefaRepository();

    public void registrar(Javalin app) {

        app.get("/tarefas-view", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            Map<String, Object> model = new HashMap<>();
            model.put("tarefas", repository.listarPorUsuario(usuarioId));
            ctx.render("tarefas.ftl", model);
        });

        app.get("/tarefas-view/{id}/editar", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int id = Integer.parseInt(ctx.pathParam("id"));
            Tarefa tarefa = repository.buscarPorId(id);

            if (tarefa == null) {
                ctx.status(404);
                return;
            }

            if (tarefa.getUsuarioId() != usuarioId) {
                ctx.status(403);
                return;
            }

            Map<String, Object> model = new HashMap<>();
            model.put("tarefa", tarefa);
            ctx.render("tarefa-editar.ftl", model);
        });

        app.post("/tarefas-view/{id}/editar", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int id = Integer.parseInt(ctx.pathParam("id"));
            Tarefa tarefa = repository.buscarPorId(id);

            if (tarefa == null || tarefa.getUsuarioId() != usuarioId) {
                ctx.status(404);
                return;
            }

            tarefa.setTitulo(ctx.formParam("titulo"));
            tarefa.setDescricao(ctx.formParam("descricao"));
            tarefa.setStatus(ctx.formParam("status"));

            dao.atualizar(tarefa);
            ctx.redirect("/tarefas-view");
        });

        app.post("/tarefas-view/{id}/delete", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int id = Integer.parseInt(ctx.pathParam("id"));
            Tarefa tarefa = repository.buscarPorId(id);

            if (tarefa == null || tarefa.getUsuarioId() != usuarioId) {
                ctx.status(404);
                return;
            }

            dao.deletar(id);
            ctx.redirect("/tarefas-view");
        });
    }
}
