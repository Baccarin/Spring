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
	
	public Pedido find(Integer id) {	
		Optional<Pedido> pedido = pedidoRepo.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())));
		
	}
	
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		return pedidoRepo.save(pedido);
	}

	public Pedido update(Pedido pedido) {
		find(pedido.getId());
		return pedidoRepo.save(pedido);
	}
}
