package com.vinim.cursomc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinim.cursomc.domain.Categoria;
import com.vinim.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null); //sozinho , obj é Optional<Category> , mas obj.orElse ele retorna um objeto do tipo Categoria;

	}
	
	public List<Categoria> listar() { 
	
		return 	repo.findAll();
	}

}
