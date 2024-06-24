package com.funcerp.projetoerpfuncionarios.controller.dto;

import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import java.time.LocalDate;

public record FuncionarioOldestDto(
    String nome,
    String idade

) {

  public static FuncionarioOldestDto fromEntity(Funcionario funcionario) {
    return new FuncionarioOldestDto(
        funcionario.getNome(),
        funcionario.getDataNascimento().until(LocalDate.now()).getYears() + " anos"
    );
  }

}
