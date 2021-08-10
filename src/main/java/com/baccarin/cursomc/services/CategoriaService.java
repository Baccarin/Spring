package com.baccarin.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.baccarin.cursomc.domain.Categoria;
import com.baccarin.cursomc.dto.CategoriaDTO;
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
		Categoria categoriaNew = find(categoria.getId());
		this.fillCategoria(categoriaNew, categoria);
		return categoriaRepo.save(categoriaNew);
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
	
	public Page<Categoria> findPageCategoria(Integer page, Integer linesPerPage, String orderBy , String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage ,Direction.valueOf(direction), orderBy);
		return categoriaRepo.findAll(pageRequest);
	}
	
	private void fillCategoria(Categoria categoriaNew, Categoria categoria) {
		categoriaNew.setNome(categoria.getNome());
	}

	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}	
		
}
