package com.funcerp.projetoerpfuncionarios.entity;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;

/**
 * Classe abstrata que representa uma pessoa.
 */
@MappedSuperclass
public abstract class Pessoa {

  private String nome;
  private LocalDate dataNascimento;

  public Pessoa() {
  }

  public Pessoa(String nome, LocalDate dataNascimento) {
    this.nome = nome;
    this.dataNascimento = dataNascimento;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public LocalDate getDataNascimento() {
    return dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }
}
