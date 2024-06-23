package com.funcerp.projetoerpfuncionarios.controller;

import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import com.funcerp.projetoerpfuncionarios.service.FuncionarioService;
import java.util.List;
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
  public List<Funcionario> findAllFuncionarios() {
    return funcionarioService.findAll();
  }

}
