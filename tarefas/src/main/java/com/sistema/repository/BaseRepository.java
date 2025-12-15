package com.sistema.repository;

import java.util.List;

public interface BaseRepository<T> {
    void salvar(T entidade);
    T buscarPorId(int id);
    List<T> listar();
    void deletar(int id);
}
