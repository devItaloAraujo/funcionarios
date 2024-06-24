package com.funcerp.projetoerpfuncionarios.service;

import com.funcerp.projetoerpfuncionarios.controller.dto.FuncionarioDto;
import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import com.funcerp.projetoerpfuncionarios.repository.FuncionarioRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço que contém a lógica de negócio para manipulação de funcionários.
 */
@Service
public class FuncionarioService {

  private final FuncionarioRepository funcionarioRepository;

  @Autowired
  public FuncionarioService(FuncionarioRepository funcionarioRepository) {
    this.funcionarioRepository = funcionarioRepository;
  }

  /**
   * Salva funcionário.
   */
  public Funcionario saveFuncionario(Funcionario funcionario) {
    return funcionarioRepository.save(funcionario);
  }

  /**
   * Deleta um funcionário por nome.
   */
  public void deleteFuncionarioByNome(String nome) {
    Optional<Funcionario> funcionario = funcionarioRepository.findByNome(nome);
    funcionario.ifPresent(funcionarioRepository::delete);
  }

  /**
   * Retorna todos os funcionários.
   */

  public List<Funcionario> findAll() {
    return funcionarioRepository.findAll();
  }

  /**
   * Aumenta o salário de todos os funcionários em 10%.
   */
  public void increaseSalarioByTenPercent() {
    List<Funcionario> allFuncionarios = funcionarioRepository.findAll();

    allFuncionarios.forEach(funcionario -> {
      BigDecimal currentSalario = funcionario.getSalario();
      BigDecimal increasedSalario = currentSalario.multiply(BigDecimal.valueOf(1.10));
      funcionario.setSalario(increasedSalario);
    });

    funcionarioRepository.saveAll(allFuncionarios);
  }

  /**
   * Agrupa os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de
   * funcionários”.
   */
  public Map<String, List<FuncionarioDto>> groupFuncionariosByFuncao() {
    List<Funcionario> allFuncionarios = funcionarioRepository.findAll();
    return allFuncionarios.stream().map(FuncionarioDto::fromEntity)
        .collect(Collectors.groupingBy(FuncionarioDto::funcao));
  }

  /**
   * Retorna lista de funcionários que fazem aniversário no(s) mês(es) fornecidos.
   */
  public List<Funcionario> findFuncionariosByBirthdayMonth(List<Integer> months) {
    List<Funcionario> allFuncionarios = funcionarioRepository.findAll();
    return allFuncionarios.stream()
        .filter(funcionario -> {
          int birthdayMonth = funcionario.getDataNascimento().getMonthValue();
          for (int month : months) {
            if (birthdayMonth == month) {
              return true;
            }
          }
          return false;
        })
        .collect(Collectors.toList());
  }

  /**
   * Retorna funcionário com maior idade.
   */
  public Funcionario findOldestFuncionario() {
    List<Funcionario> allFuncionarios = funcionarioRepository.findAll();
    return allFuncionarios.stream()
        .max((funcionario1, funcionario2) -> {
          int age1 = funcionario1.getDataNascimento().until(LocalDate.now()).getYears();
          int age2 = funcionario2.getDataNascimento().until(LocalDate.now()).getYears();
          return Integer.compare(age1, age2);
        })
        .orElse(null);
  }

  /**
   * Retorna lista de funcionários em ordem alfabética.
   */
  public List<Funcionario> findAllOrderedByName() {
    List<Funcionario> allFuncionarios = funcionarioRepository.findAll();
    return allFuncionarios.stream()
        .sorted((funcionario1, funcionario2) -> funcionario1.getNome()
            .compareTo(funcionario2.getNome()))
        .collect(Collectors.toList());
  }

  /**
   * Retorna o total da soma dos salários de todos os funcionários.
   */
  public BigDecimal getTotalSalarios() {
    List<Funcionario> allFuncionarios = funcionarioRepository.findAll();
    return allFuncionarios.stream()
        .map(Funcionario::getSalario)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

  }
}