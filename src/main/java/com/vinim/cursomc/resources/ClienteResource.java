package com.vinim.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinim.cursomc.domain.Cliente;
import com.vinim.cursomc.service.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	
	@GetMapping(value="{id}")
	public  ResponseEntity<Cliente> find(@PathVariable Integer id){
			Cliente obj = service.buscar(id);
			return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping
	public List<Cliente> listar(){
	return	service.listarCliente();
	}

}
