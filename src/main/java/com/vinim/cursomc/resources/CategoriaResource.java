package com.vinim.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinim.cursomc.domain.Categoria;
import com.vinim.cursomc.service.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
     @Autowired 	
	 private CategoriaService service;
	
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Categoria obj  = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public List<Categoria> Listar() {

		return service.listar();
	
	}

}
