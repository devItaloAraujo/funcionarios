package com.funcerp.projetoerpfuncionarios.repository;

import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface que representa o repositório de funcionários.
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

  /**
   * Deleta um funcionário por nome.
   *
   * @param nome Nome do funcionário.
   */
  void deleteByNome(String nome);
}
