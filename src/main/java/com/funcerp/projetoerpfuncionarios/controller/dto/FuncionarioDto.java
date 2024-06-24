package com.funcerp.projetoerpfuncionarios.controller.dto;


import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


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
        NumberFormat.getInstance(new Locale("pt", "BR")).format(funcionario.getSalario()),
        funcionario.getFuncao()
    );
  }
}

