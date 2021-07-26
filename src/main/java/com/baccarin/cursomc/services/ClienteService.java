package com.baccarin.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baccarin.cursomc.domain.Cliente;
import com.baccarin.cursomc.repositories.ClienteRepository;
import com.baccarin.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepo;
	
	public Cliente buscar(Integer id) {	
		Optional<Cliente> cliente = clienteRepo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())));
		
	}
	
}
