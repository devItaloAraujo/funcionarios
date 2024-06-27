package com.funcerp.projetoerpfuncionarios;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import com.funcerp.projetoerpfuncionarios.service.FuncionarioService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DisplayName("Funcionarios ERP Test")
public class FuncionariosERPIntegrationTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  FuncionarioService funcionarioService;

  @Test
  @DisplayName("Deve retornar lista de funcionarios ao realizar requisição GET na raiz do projeto")
  public void testFindAllFuncionarios() throws Exception {
    List<Funcionario> funcionarios = funcionarioService.findAll();
    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(10)))
        .andExpect(jsonPath("$[0].nome").value(funcionarios.get(0).getNome()))
        .andExpect(jsonPath("$[9].nome").value(funcionarios.get(9).getNome()));
  }

  @Test
  @DisplayName("Deve deletar funcionário por nome quando requisição DELETE é feita")
  public void testDeleteFuncionarioByNome() throws Exception {
    String nome = "João";

    mockMvc.perform(delete("/delete/" + nome))
        .andExpect(status().isOk());

    List<Funcionario> funcionarios = funcionarioService.findAll();

    boolean exists = funcionarios.stream()
        .anyMatch(funcionario -> funcionario.getNome().equals(nome));

    assertEquals(9, funcionarios.size(), "Deveriam ter 9 nomes na lista de funcionários");
    assertFalse(exists, "Funcionario com nome " + nome + " deveria ter sido deletado");

    Funcionario deletedFuncionario = new Funcionario("João", "12/05/1990", "2284.38", "Operador");
    funcionarioService.saveFuncionario(deletedFuncionario);
  }

  @Test
  @DisplayName("Deve aumentar salário em dez por cento quando requisição PATCH é feita")
  public void testIncreaseSalarioByTenPercent() throws Exception {
    mockMvc.perform(patch("/aumenta-salario"))
        .andExpect(status().isOk());

    List<Funcionario> funcionarios = funcionarioService.findAll();

    assertEquals(BigDecimal.valueOf(2210.38), funcionarios.get(0).getSalario(),
        "Salário de Maria deveria ter sido aumentado em 10%");

    funcionarioService.decreaseSalarioByTenPercent();
  }

  @Test
  @DisplayName("Deve retornar objeto correto ao agrupar por funções")
  public void testGroupFuncionariosByFuncao() throws Exception {
    mockMvc.perform(get("/funcoes"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$.Gerente").isNotEmpty())
        .andExpect(jsonPath("$.Gerente").isArray())
        .andExpect(jsonPath("$.Gerente", hasSize(2)))
        .andExpect(jsonPath("$.Gerente[0].nome").value("Laura"));
  }

  @Test
  @DisplayName("Deve retornar funcionários que fazem aniversário nos meses fornecidos")
  public void testFindFuncionariosByAniversario() throws Exception {

    mockMvc.perform(get("/aniversario/").param("months", "10", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$", hasSize(3)))
        .andExpect(jsonPath("$[0].nome").value("Maria"))
        .andExpect(jsonPath("$[1].nome").value("Miguel"))
        .andExpect(jsonPath("$[2].nome").value("Alice"));
  }

  @Test
  @DisplayName("Deve retornar o funcionário mais velho quando requisitado")
  public void testFindOldestFuncionario() throws Exception {

    mockMvc.perform(get("/mais-velho"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome").value("Caio"));
  }

  @Test
  @DisplayName("Deve retornar todos os funcionários ordenados por nome quando requisitado")
  public void testFindAllFuncionariosOrdered() throws Exception {

    mockMvc.perform(get("/ordenada"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$", hasSize(10)))
        .andExpect(jsonPath("$[0].nome").value("Alice"))
        .andExpect(jsonPath("$[9].nome").value("Miguel"));
  }

  @Test
  @DisplayName("Deve retornar o total de salários")
  public void testGetTotalSalario() throws Exception {

    mockMvc.perform(get("/total"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$").value("R$ 48.563,31"));
  }

  @Test
  @DisplayName("Deve retornar lista de funcionarios e quantos salários mínimos eles recebem")
  public void testGetFuncionariosSalarioMin() throws Exception {

    mockMvc.perform(get("/salarios-min"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty())
        .andExpect(jsonPath("$", hasSize(10)))
        .andExpect(jsonPath("$[0].nome").value("Maria"))
        .andExpect(jsonPath("$[0].salarios").value("1.66"));
  }
}
