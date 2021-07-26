package com.baccarin.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baccarin.cursomc.domain.Pedido;
import com.baccarin.cursomc.repositories.PedidoRepository;
import com.baccarin.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	public Pedido buscar(Integer id) {	
		Optional<Pedido> cliente = pedidoRepo.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())));
		
	}
}
