package com.sistema.repository;

import com.sistema.dao.UsuarioDAO;
import com.sistema.model.Usuario;

import java.util.List;

public class UsuarioRepository {

    private UsuarioDAO dao = new UsuarioDAO();

    public void salvar(Usuario usuario) {
        if (usuario.getId() == 0) {
            dao.inserir(usuario);
        } else {
            dao.atualizar(usuario);
        }
    }

    public Usuario buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public List<Usuario> listar() {
        return dao.listar();
    }

    public void deletar(int id) {
        dao.deletar(id);
    }

    public Usuario buscarPorEmail(String email) {
        return dao.buscarPorEmail(email);
    }
}
