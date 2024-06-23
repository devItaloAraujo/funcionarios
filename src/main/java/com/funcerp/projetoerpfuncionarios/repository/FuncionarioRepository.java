package com.funcerp.projetoerpfuncionarios.repository;

import com.funcerp.projetoerpfuncionarios.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
