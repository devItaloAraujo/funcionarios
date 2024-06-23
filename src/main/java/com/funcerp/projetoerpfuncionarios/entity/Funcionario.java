package com.funcerp.projetoerpfuncionarios.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Classe que representa um funcionário.
 */
@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private BigDecimal salario;
  private String funcao;

  public Funcionario() {
    super();
  }

  /**
   * Construtor da classe Funcionario.
   *
   * @param nome           Nome do funcionário.
   * @param dataNascimento Data de nascimento do funcionário.
   * @param salario        Salário do funcionário.
   * @param funcao         Função do funcionário.
   */
  public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
    super(nome, dataNascimento);
    this.salario = salario;
    this.funcao = funcao;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getSalario() {
    return salario;
  }

  public void setSalario(BigDecimal salario) {
    this.salario = salario;
  }

  public String getFuncao() {
    return funcao;
  }

  public void setFuncao(String funcao) {
    this.funcao = funcao;
  }
}
