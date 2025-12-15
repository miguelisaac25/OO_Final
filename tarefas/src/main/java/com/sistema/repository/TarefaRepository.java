package com.sistema.repository;

import com.sistema.dao.TarefaDAO;
import com.sistema.model.Tarefa;

import java.util.List;

public class TarefaRepository implements BaseRepository<Tarefa> {

    private TarefaDAO dao = new TarefaDAO();

    @Override
    public void salvar(Tarefa entidade) {
        if (entidade.getId() == 0) {
            dao.inserir(entidade);
        } else {
            dao.atualizar(entidade);
        }
    }

    @Override
    public Tarefa buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Tarefa> listar() {
        return dao.listar();
    }

    @Override
    public void deletar(int id) {
        dao.deletar(id);
    }

    public List<Tarefa> listarPorUsuario(int usuarioId) {
        return dao.listarPorUsuario(usuarioId);
    }

    public List<Tarefa> listarPorProjeto(int projetoId) {
        return dao.listarPorProjeto(projetoId);
    }
}
