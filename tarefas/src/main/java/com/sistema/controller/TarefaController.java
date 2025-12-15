package com.sistema.controller;

import java.util.HashMap;
import java.util.Map;

import com.sistema.model.Tarefa;
import com.sistema.repository.TarefaRepository;
import io.javalin.Javalin;

public class TarefaController {

    private final TarefaRepository repository = new TarefaRepository();

    public void registrar(Javalin app) {

        app.post("/tarefas", ctx -> {
            Tarefa tarefa = ctx.bodyAsClass(Tarefa.class);
            repository.salvar(tarefa);
            ctx.status(201).result("Tarefa criada com sucesso");
        });

        app.get("/tarefas", ctx -> {
            ctx.json(repository.listar());
        });

        app.get("/tarefas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Tarefa tarefa = (Tarefa) repository.buscarPorId(id);

            if (tarefa == null) {
                ctx.status(404).result("Tarefa não encontrada");
            } else {
                ctx.json(tarefa);
            }
        });

        app.put("/tarefas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Tarefa tarefa = ctx.bodyAsClass(Tarefa.class);
            tarefa.setId(id);

            repository.salvar(tarefa);
            ctx.result("Tarefa atualizada com sucesso");
        });

        app.delete("/tarefas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            repository.deletar(id);
            ctx.result("Tarefa removida com sucesso");
        });

        app.get("/tarefas-usuario/{usuarioId}", ctx -> {
            int usuarioId = Integer.parseInt(ctx.pathParam("usuarioId"));
            ctx.json(repository.listarPorUsuario(usuarioId));
        });

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

        app.post("/tarefas-view", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            Tarefa tarefa = new Tarefa();
            tarefa.setTitulo(ctx.formParam("titulo"));
            tarefa.setDescricao(ctx.formParam("descricao"));
            tarefa.setStatus(ctx.formParam("status"));
            tarefa.setUsuarioId(usuarioId);

            repository.salvar(tarefa);
            ctx.redirect("/tarefas-view");
        });

    }
}
