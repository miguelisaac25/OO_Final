package com.sistema.repository;

import com.sistema.dao.ProjetoDAO;
import com.sistema.model.Projeto;

import java.util.List;

public class ProjetoRepository {

    private ProjetoDAO dao = new ProjetoDAO();

    public List<Projeto> listarPorUsuario(int usuarioId) {
        return dao.listarPorUsuario(usuarioId);
    }
}