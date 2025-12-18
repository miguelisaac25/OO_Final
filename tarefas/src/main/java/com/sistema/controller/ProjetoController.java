package com.sistema.controller;

import com.sistema.dao.ProjetoDAO;
import com.sistema.dao.TarefaDAO;
import com.sistema.model.Projeto;
import com.sistema.model.Tarefa;
import com.sistema.repository.ProjetoRepository;
import com.sistema.repository.TarefaRepository;
import io.javalin.Javalin;

import java.util.HashMap;
import java.util.Map;

public class ProjetoController {

    private final ProjetoDAO projetoDAO = new ProjetoDAO();
    private final TarefaDAO tarefaDAO = new TarefaDAO();

    private final ProjetoRepository projetoRepo = new ProjetoRepository();
    private final TarefaRepository tarefaRepo = new TarefaRepository();

    public void registrar(Javalin app) {

        app.get("/projetos-view", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            Map<String, Object> model = new HashMap<>();
            model.put("projetos", projetoRepo.listarPorUsuario(usuarioId));
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

            projetoDAO.inserir(projeto);
            ctx.redirect("/projetos-view");
        });

        app.get("/projetos-view/{id}", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            Projeto projeto = projetoDAO.buscarPorId(projetoId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId) {
                ctx.status(404);
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
            Projeto projeto = projetoDAO.buscarPorId(projetoId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId) {
                ctx.status(404);
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
            Projeto projeto = projetoDAO.buscarPorId(projetoId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId) {
                ctx.status(404);
                return;
            }

            projeto.setNome(ctx.formParam("nome"));
            projeto.setDescricao(ctx.formParam("descricao"));

            projetoDAO.atualizar(projeto);
            ctx.redirect("/projetos-view");
        });

        app.post("/projetos-view/{id}/delete", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            Projeto projeto = projetoDAO.buscarPorId(projetoId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId) {
                ctx.status(404);
                return;
            }

            tarefaRepo.listarPorProjeto(projetoId)
                    .forEach(t -> tarefaDAO.deletar(t.getId()));

            projetoDAO.deletar(projetoId);
            ctx.redirect("/projetos-view");
        });

        app.post("/projetos-view/{id}/tarefas", ctx -> {
            Integer usuarioId = ctx.sessionAttribute("usuarioId");
            if (usuarioId == null) {
                ctx.redirect("/login-view");
                return;
            }

            int projetoId = Integer.parseInt(ctx.pathParam("id"));
            Projeto projeto = projetoDAO.buscarPorId(projetoId);

            if (projeto == null || projeto.getUsuarioId() != usuarioId) {
                ctx.status(404);
                return;
            }

            Tarefa tarefa = new Tarefa();
            tarefa.setTitulo(ctx.formParam("titulo"));
            tarefa.setDescricao(ctx.formParam("descricao"));
            tarefa.setStatus("Pendente");
            tarefa.setProjetoId(projetoId);
            tarefa.setUsuarioId(usuarioId);

            tarefaDAO.inserir(tarefa);
            ctx.redirect("/projetos-view/" + projetoId);
        });
    }
}
