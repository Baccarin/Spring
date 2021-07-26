package com.baccarin.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baccarin.cursomc.domain.Categoria;
import com.baccarin.cursomc.repositories.CategoriaRepository;
import com.baccarin.cursomc.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Categoria buscar(Integer id) {
		
		Optional<Categoria> cat = categoriaRepo.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())));
		
	}
	
	public Categoria insert(Categoria categoria) {
		return categoriaRepo.save(categoria);
	}
	
}
