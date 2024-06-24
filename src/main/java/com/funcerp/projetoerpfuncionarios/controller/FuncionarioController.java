package com.funcerp.projetoerpfuncionarios.controller;

import com.funcerp.projetoerpfuncionarios.controller.dto.FuncionarioDto;
import com.funcerp.projetoerpfuncionarios.service.FuncionarioService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
