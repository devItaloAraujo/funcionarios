package com.funcerp.projetoerpfuncionarios.controller.dto;


import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import java.time.format.DateTimeFormatter;


public record FuncionarioDto(
    String nome,
    String dataNascimento,
    String salario,
    String funcao

) {

  public static FuncionarioDto fromEntity(Funcionario funcionario) {
    return new FuncionarioDto(
        funcionario.getNome(),
        funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        funcionario.getSalario().toString(),
        funcionario.getFuncao()
    );

  }
}

