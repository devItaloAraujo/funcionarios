package com.funcerp.projetoerpfuncionarios.controller.dto;


import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Classe que representa um funcionário.
 */
public record FuncionarioDto(
    String nome,
    String dataNascimento,
    String salario,
    String funcao

) {

  /**
   * Método que cria um FuncionarioDto a partir de um Funcionario.
   */
  public static FuncionarioDto fromEntity(Funcionario funcionario) {
    return new FuncionarioDto(
        funcionario.getNome(),
        funcionario.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        NumberFormat.getInstance(new Locale("pt", "BR")).format(funcionario.getSalario()),
        funcionario.getFuncao()
    );
  }
}

