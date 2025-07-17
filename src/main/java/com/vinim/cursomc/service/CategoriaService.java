package com.vinim.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vinim.cursomc.domain.Categoria;
import com.vinim.cursomc.repositories.CategoriaRepository;
import com.vinim.cursomc.service.exceptions.DataIntegrityException;
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
	
	public void delete(Integer id) {
		this.buscar(id);
		try {
			
			this.repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy );
		return repo.findAll(pageRequest);
	}
}
