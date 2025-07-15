package com.vinim.cursomc.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinim.cursomc.domain.Categoria;
import com.vinim.cursomc.repositories.CategoriaRepository;
import com.vinim.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

	
	

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(	"Objeto não encontrado! Id:" + 	id + ", Tipo: " + Categoria.class.getName())); //sozinho , obj é Optional<Category> , mas obj.orElse ele retorna um objeto do tipo Categoria;

	}
	
	public List<Categoria> listar() {
	
		return repo.findAll();
		}
    
	public Categoria insert(Categoria obj) {
		
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		this.buscar(obj.getId());
		return repo.save(obj);
	}
}
