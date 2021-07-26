package com.baccarin.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baccarin.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
