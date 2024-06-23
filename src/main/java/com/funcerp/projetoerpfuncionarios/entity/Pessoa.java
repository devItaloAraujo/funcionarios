package com.funcerp.projetoerpfuncionarios.entity;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe abstrata que representa uma pessoa.
 */
@MappedSuperclass
public abstract class Pessoa {

  private String nome;
  private LocalDate dataNascimento;

  public Pessoa() {
  }

  /**
   * Construtor da classe Pessoa.
   *
   * @param nome           Nome da pessoa.
   * @param dataNascimento Data de nascimento da pessoa.
   */
  public Pessoa(String nome, String dataNascimento) {
    this.nome = nome;
    this.dataNascimento = LocalDate.parse(dataNascimento,
        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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
