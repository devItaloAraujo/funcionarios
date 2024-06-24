package com.funcerp.projetoerpfuncionarios.controller.dto;

import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import java.time.LocalDate;

/**
 * Classe que representa um funcionário mais velho.
 */
public record FuncionarioOldestDto(
    String nome,
    String idade

) {

  /**
   * Método que cria um FuncionarioOldestDto a partir de um Funcionario.
   */
  public static FuncionarioOldestDto fromEntity(Funcionario funcionario) {
    return new FuncionarioOldestDto(
        funcionario.getNome(),
        funcionario.getDataNascimento().until(LocalDate.now()).getYears() + " anos"
    );
  }

}
