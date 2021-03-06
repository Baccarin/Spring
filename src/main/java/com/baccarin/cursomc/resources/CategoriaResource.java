package com.baccarin.cursomc.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.baccarin.cursomc.domain.Categoria;
import com.baccarin.cursomc.domain.Cliente;
import com.baccarin.cursomc.dto.CategoriaDTO;
import com.baccarin.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = categoriaService.find(id);
		return ResponseEntity.ok(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
		Categoria categoria = new Categoria(categoriaDTO);
		categoria = categoriaService.insert(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO categoriaDTO, @PathVariable Integer id) {
		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria.setId(id);
		categoria = categoriaService.update(categoria);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> listaCategorias = categoriaService.findAll();
		List<CategoriaDTO> listaCategoriaDTO = new ArrayList<>();
		listaCategorias.forEach(categoria -> {
			listaCategoriaDTO.add(new CategoriaDTO(categoria));
		});

		// Sugest??o do video
		// List<CategoriaDTO> lista = listaCategorias.stream().map(categoria1 -> new
		// CategoriaDTO(categoria1)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listaCategoriaDTO);
	}

	//URI para chamada do servi??o page?linesPerPage=5&orderBy=id&direction=DESC
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy , 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Categoria> pageCategoria = categoriaService.findPageCategoria(page, linesPerPage, orderBy, direction);
		
		Page<CategoriaDTO> pageCategoriaDTO = pageCategoria.map(categoria -> new
				 CategoriaDTO(categoria));

		return ResponseEntity.ok().body(pageCategoriaDTO);
	}

}
