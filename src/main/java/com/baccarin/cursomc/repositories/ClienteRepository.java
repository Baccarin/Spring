package com.baccarin.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.baccarin.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
