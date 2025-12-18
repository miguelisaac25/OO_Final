package com.sistema;

import com.sistema.config.DatabaseConfig;
import com.sistema.controller.ProjetoController;
import com.sistema.controller.TarefaController;
import com.sistema.controller.UsuarioController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinFreemarker;

public class App {

  public static void main(String[] args) {

    Javalin app = Javalin.create(config -> {
      config.fileRenderer(new JavalinFreemarker());
      config.staticFiles.add(staticFiles -> {
        staticFiles.directory = "public";
        staticFiles.location = Location.CLASSPATH;
      });
    }).start(7000);

    new UsuarioController().registrar(app);
    new ProjetoController().registrar(app);
    new TarefaController().registrar(app);

    System.out.println("Sistema iniciado na porta 7000");

    DatabaseConfig.getConnection();
    System.out.println("Conectou no banco certo");
  }
}
