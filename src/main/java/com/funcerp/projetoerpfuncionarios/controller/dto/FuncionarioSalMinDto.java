package com.funcerp.projetoerpfuncionarios.controller.dto;

import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import java.math.BigDecimal;
import java.math.RoundingMode;

public record FuncionarioSalMinDto(
    String nome,
    BigDecimal salarios
) {

  /**
   * Retorna quantos salários mínimos um funcionário recebe.
   */
  private static BigDecimal getSalariosMinimos(Funcionario funcionario) {
    BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
    return funcionario.getSalario().divide(salarioMinimo, RoundingMode.HALF_DOWN);
  }

  public static FuncionarioSalMinDto fromEntity(Funcionario funcionario) {
    return new FuncionarioSalMinDto(funcionario.getNome(), getSalariosMinimos(funcionario));
  }

}
