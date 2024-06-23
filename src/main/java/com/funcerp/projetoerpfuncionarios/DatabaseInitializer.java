package com.funcerp.projetoerpfuncionarios;

import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import com.funcerp.projetoerpfuncionarios.service.FuncionarioService;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe que inicializa o banco de dados com alguns funcionários.
 */
@Configuration
public class DatabaseInitializer {

  /**
   * Inicializa o banco de dados com alguns funcionários.
   *
   * @param funcionarioService Serviço de funcionários.
   * @return Um CommandLineRunner que inicializa o banco de dados.
   */
  @Bean
  public CommandLineRunner initDatabase(FuncionarioService funcionarioService) {
    return args -> {
      if (funcionarioService.findAll().isEmpty()) {
        List<Funcionario> funcionarios = Arrays.asList(
            new Funcionario("Maria", "18/10/2000", "2009.44", "Operador"),
            new Funcionario("João", "12/05/1990", "2284.38", "Operador"),
            new Funcionario("Caio", "02/05/1961", "9836.14", "Coordenador"),
            new Funcionario("Miguel", "14/10/1988", "19119.88", "Diretor"),
            new Funcionario("Alice", "05/01/1995", "2234.68", "Recepcionista"),
            new Funcionario("Heitor", "19/11/1999", "1582.72", "Operador"),
            new Funcionario("Arthur", "31/03/1993", "4071.84", "Contador"),
            new Funcionario("Laura", "08/07/1994", "3017.45", "Gerente"),
            new Funcionario("Heloísa", "24/05/2003", "1606.85", "Eletricista"),
            new Funcionario("Helena", "02/09/1996", "2799.93", "Gerente")
        );

        funcionarios.forEach(funcionarioService::saveFuncionario);
      }
    };
  }
}