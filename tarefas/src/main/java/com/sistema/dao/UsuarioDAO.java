package com.sistema.dao;

import com.sistema.config.DatabaseConfig;
import com.sistema.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void inserir(Usuario usuario) {
        String sql = "INSERT INTO usuarios (email, senha) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> listar() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuarios.add(usuario);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public void deletar(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Usuario buscarPorEmail(String email) {
        String sql = "SELECT * FROM usuarios WHERE email = ?";
        Usuario usuario = null;

        try (Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }

}
