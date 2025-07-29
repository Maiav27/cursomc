package com.vinim.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vinim.cursomc.domain.Produto;
import com.vinim.cursomc.dto.ProdutoDTO;
import com.vinim.cursomc.resources.utils.URL;
import com.vinim.cursomc.service.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	ProdutoService service;
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids =URL.decodeIntList(categorias);
		Page<Produto> list = service.search( nomeDecoded, ids,  page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		
		return ResponseEntity.ok().body(listDTO);
	}
			
			

}
