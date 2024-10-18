package com.example.banco_dados.Interface;

import com.example.banco_dados.Model.Pessoa;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa, Long> {
}
