package com.vinim.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.vinim.cursomc.domain.Categoria;
import com.vinim.cursomc.domain.Produto;
import com.vinim.cursomc.repositories.CategoriaRepository;
import com.vinim.cursomc.repositories.ProdutoRepository;
import com.vinim.cursomc.service.exceptions.ObjectNotFoundException;
@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repo;
	@Autowired
	CategoriaRepository repoCategoria;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy );
		List<Categoria> categorias = repoCategoria.findAllById(ids);
		return repo.search(nome,categorias, pageRequest);
	}

}
