package com.vinim.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vinim.cursomc.domain.Categoria;
import com.vinim.cursomc.domain.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	
    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE  obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> search(String nome, List<Categoria> categorias, Pageable pageRequest);
   // Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest); se esse fosse o nome do da função não precisaria da query
    
		
	

}
