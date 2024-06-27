package com.funcerp.projetoerpfuncionarios.controller;

import com.funcerp.projetoerpfuncionarios.controller.dto.FuncionarioDto;
import com.funcerp.projetoerpfuncionarios.controller.dto.FuncionarioOldestDto;
import com.funcerp.projetoerpfuncionarios.controller.dto.FuncionarioSalMinDto;
import com.funcerp.projetoerpfuncionarios.service.FuncionarioService;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe que representa um controlador REST para funcionários.
 */
@RestController
@RequestMapping("/")
public class FuncionarioController {

  private final FuncionarioService funcionarioService;

  @Autowired
  public FuncionarioController(FuncionarioService funcionarioService) {
    this.funcionarioService = funcionarioService;
  }

  @GetMapping
  public List<FuncionarioDto> findAllFuncionarios() {
    return funcionarioService.findAll().stream()
        .map(FuncionarioDto::fromEntity).collect(Collectors.toList());
  }

  @DeleteMapping("/delete/{nome}")
  public void deleteFuncionarioByNome(@PathVariable String nome) {
    funcionarioService.deleteFuncionarioByNome(nome);
  }

  @PatchMapping("/aumenta-salario")
  public void increaseSalarioByTenPercent() {
    funcionarioService.increaseSalarioByTenPercent();
  }

  @GetMapping("/funcoes")
  public Map<String, List<FuncionarioDto>> groupFuncionariosByFuncao() {
    return funcionarioService.groupFuncionariosByFuncao();
  }

  @GetMapping("/aniversario/")
  public List<FuncionarioDto> findFuncionariosByAniversario(@RequestParam List<Integer> months) {
    return funcionarioService.findFuncionariosByBirthdayMonth(months).stream()
        .map(FuncionarioDto::fromEntity).collect(Collectors.toList());
  }

  @GetMapping("/mais-velho")
  public FuncionarioOldestDto findOldestFuncionario() {
    return FuncionarioOldestDto.fromEntity(funcionarioService.findOldestFuncionario());
  }

  @GetMapping("/ordenada")
  public List<FuncionarioDto> findAllFuncionariosOrdered() {
    return funcionarioService.findAllOrderedByName().stream()
        .map(FuncionarioDto::fromEntity).collect(Collectors.toList());
  }

  @GetMapping("/total")
  public String getTotalSalario() {
    return "R$ " + NumberFormat.getInstance(new Locale("pt", "BR"))
        .format(funcionarioService.getTotalSalarios());
  }

  @GetMapping("/salarios-min")
  public List<FuncionarioSalMinDto> getFuncionariosSalarioMin() {
    return funcionarioService.findAll().stream()
        .map(FuncionarioSalMinDto::fromEntity).collect(Collectors.toList());
  }
}
