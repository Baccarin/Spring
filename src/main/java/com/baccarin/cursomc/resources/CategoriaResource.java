package com.baccarin.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baccarin.cursomc.domain.Categoria;
import com.baccarin.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	// @Autowired instanciar automatico
	@Autowired
	private CategoriaService catService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	
	// ResponseEntity<?> retorna qualquer tipo de objeto
	public ResponseEntity<?> listar(@PathVariable Integer id) {
		Categoria obj = catService.buscar(id);
		return ResponseEntity.ok(obj);

	}

}
