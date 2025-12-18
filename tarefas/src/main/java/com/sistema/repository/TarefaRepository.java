package com.sistema.repository;

import com.sistema.dao.TarefaDAO;
import com.sistema.model.Tarefa;

import java.util.List;

public class TarefaRepository {

    private TarefaDAO dao = new TarefaDAO();

    public Tarefa buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public List<Tarefa> listarPorUsuario(int usuarioId) {
        return dao.listarPorUsuario(usuarioId);
    }

    public List<Tarefa> listarPorProjeto(int projetoId) {
        return dao.listarPorProjeto(projetoId);
    }

}
