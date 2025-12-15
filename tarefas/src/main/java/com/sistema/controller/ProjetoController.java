package com.sistema.controller;

import java.util.HashMap;
import java.util.Map;

import com.sistema.model.Projeto;
import com.sistema.model.Tarefa;
import com.sistema.repository.ProjetoRepository;
import com.sistema.repository.TarefaRepository;

import io.javalin.Javalin;

public class ProjetoController {

    private ProjetoRepository repository = new ProjetoRepository();
    private TarefaRepository tarefaRepo = new TarefaRepository();

    public void registrar(Javalin app) {

        app.get("/projetos-view", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            Map<String, Object> model = new HashMap<>();
            model.put("projetos", repository.listarPorUsuario(usuarioId));
            ctx.render("projetos.ftl", model);
        });

        app.post("/projetos-view", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            Projeto projeto = new Projeto();
            projeto.setNome(ctx.formParam("nome"));
            projeto.setDescricao(ctx.formParam("descricao"));
            projeto.setUsuarioId(usuarioId);

            repository.salvar(projeto);
            ctx.redirect("/projetos-view");
        });

        app.get("/projetos-view/{id}", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            Projeto projeto = repository.buscarPorId(projetoId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId) {
                ctx.status(404).result("Projeto não encontrado");
                return;
            }

            Map<String, Object> model = new HashMap<>();
            model.put("projeto", projeto);
            model.put("tarefas", tarefaRepo.listarPorProjeto(projetoId));

            ctx.render("projeto-detalhe.ftl", model);
        });

        app.get("/projetos-view/{id}/editar", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            Projeto projeto = repository.buscarPorId(projetoId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId) {
                ctx.status(404).result("Projeto não encontrado");
                return;
            }

            Map<String, Object> model = new HashMap<>();
            model.put("projeto", projeto);
            ctx.render("projeto-editar.ftl", model);
        });

        app.post("/projetos-view/{id}/editar", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            Projeto projeto = repository.buscarPorId(projetoId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId) {
                ctx.status(404).result("Projeto não encontrado");
                return;
            }

            projeto.setNome(ctx.formParam("nome"));
            projeto.setDescricao(ctx.formParam("descricao"));

            repository.salvar(projeto);
            ctx.redirect("/projetos-view");
        });

        app.post("/projetos-view/{id}/delete", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            Projeto projeto = repository.buscarPorId(projetoId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId) {
                ctx.status(404).result("Projeto não encontrado");
                return;
            }

            tarefaRepo.listarPorProjeto(projetoId).forEach(t -> tarefaRepo.deletar(t.getId()));
            repository.deletar(projetoId);

            ctx.redirect("/projetos-view");
        });

        app.post("/projetos-view/{id}/tarefas", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            Projeto projeto = repository.buscarPorId(projetoId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId) {
                ctx.status(404).result("Projeto não encontrado");
                return;
            }

            Tarefa tarefa = new Tarefa();
            tarefa.setTitulo(ctx.formParam("titulo"));
            tarefa.setDescricao(ctx.formParam("descricao"));
            tarefa.setStatus("Pendente");
            tarefa.setProjetoId(projetoId);
            tarefa.setUsuarioId(usuarioId);

            tarefaRepo.salvar(tarefa);
            ctx.redirect("/projetos-view/" + projetoId);
        });

        app.post("/projetos-view/{id}/tarefas/{tarefaId}/toggle", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            int tarefaId = Integer.parseInt(ctx.pathParam("tarefaId"));

            Tarefa tarefa = tarefaRepo.buscarPorId(tarefaId);

            if (tarefa == null || tarefa.getUsuarioId() != usuarioId || tarefa.getProjetoId() != projetoId) {
                ctx.status(404).result("Tarefa não encontrada");
                return;
            }

            if ("Concluida".equals(tarefa.getStatus())) {
                tarefa.setStatus("Pendente");
            } else {
                tarefa.setStatus("Concluida");
            }

            tarefaRepo.salvar(tarefa);
            ctx.redirect("/projetos-view/" + projetoId);
        });

        app.get("/projetos-view/{id}/tarefas/{tarefaId}/editar", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            int tarefaId = Integer.parseInt(ctx.pathParam("tarefaId"));

            Projeto projeto = repository.buscarPorId(projetoId);
            Tarefa tarefa = tarefaRepo.buscarPorId(tarefaId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId || tarefa == null
                    || tarefa.getUsuarioId() != usuarioId || tarefa.getProjetoId() != projetoId) {
                ctx.status(404).result("Não encontrado");
                return;
            }

            Map<String, Object> model = new HashMap<>();
            model.put("projeto", projeto);
            model.put("tarefa", tarefa);
            ctx.render("tarefa-editar.ftl", model);
        });

        app.post("/projetos-view/{id}/tarefas/{tarefaId}/editar", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            int tarefaId = Integer.parseInt(ctx.pathParam("tarefaId"));

            Projeto projeto = repository.buscarPorId(projetoId);
            Tarefa tarefa = tarefaRepo.buscarPorId(tarefaId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId || tarefa == null
                    || tarefa.getUsuarioId() != usuarioId || tarefa.getProjetoId() != projetoId) {
                ctx.status(404).result("Não encontrado");
                return;
            }

            tarefa.setTitulo(ctx.formParam("titulo"));

            tarefaRepo.salvar(tarefa);
            ctx.redirect("/projetos-view/" + projetoId);
        });

        app.post("/projetos-view/{id}/tarefas/{tarefaId}/delete", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            int tarefaId = Integer.parseInt(ctx.pathParam("tarefaId"));

            Projeto projeto = repository.buscarPorId(projetoId);
            Tarefa tarefa = tarefaRepo.buscarPorId(tarefaId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId || tarefa == null
                    || tarefa.getUsuarioId() != usuarioId || tarefa.getProjetoId() != projetoId) {
                ctx.status(404).result("Não encontrado");
                return;
            }

            tarefaRepo.deletar(tarefaId);
            ctx.redirect("/projetos-view/" + projetoId);
        });
    }
}
