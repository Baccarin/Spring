package com.baccarin.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.baccarin.cursomc.domain.Categoria;
import com.baccarin.cursomc.repositories.CategoriaRepository;
import com.baccarin.cursomc.services.exceptions.DataIntegrityExcpetion;
import com.baccarin.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepo;

	public Categoria find(Integer id) {
		Optional<Categoria> cat = categoriaRepo.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException(
				("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())));

	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepo.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return categoriaRepo.save(categoria);
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepo.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityExcpetion("Não é possível excluir uma categoria que possua produtos relacionados");
		}
	}
	
	public List<Categoria> findAll(){
		return categoriaRepo.findAll();
	}

}
