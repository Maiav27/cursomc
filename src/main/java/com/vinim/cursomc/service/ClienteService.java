package com.vinim.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinim.cursomc.domain.Cliente;
import com.vinim.cursomc.repositories.ClienteRepository;
import com.vinim.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
	    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:" + id + "Tipo:" + Cliente.class.getName()));
	}
	
	
	public List<Cliente> listarCliente(){
		 return repo.findAll();
	}
  
}
