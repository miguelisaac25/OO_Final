package com.sistema.repository;

import com.sistema.dao.ProjetoDAO;
import com.sistema.model.Projeto;

import java.util.List;

public class ProjetoRepository implements BaseRepository<Projeto> {

    private final ProjetoDAO dao = new ProjetoDAO();

    @Override
    public void salvar(Projeto entidade) {
        if (entidade.getId() == 0) {
            dao.inserir(entidade);
        } else {
            dao.atualizar(entidade);
        }
    }

    @Override
    public Projeto buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    @Override
    public List<Projeto> listar() {
        return dao.listar();
    }

    @Override
    public void deletar(int id) {
        dao.deletar(id);
    }

    public List<Projeto> listarPorUsuario(int usuarioId) {
        return dao.listarPorUsuario(usuarioId);
    }
}
