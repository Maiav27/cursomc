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
import com.vinim.cursomc.domain.Cliente;
import com.vinim.cursomc.domain.Cliente;
import com.vinim.cursomc.dto.ClienteDTO;
import com.vinim.cursomc.repositories.ClienteRepository;
import com.vinim.cursomc.service.exceptions.DataIntegrityException;
import com.vinim.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	
	
	
	
	public List<Cliente> listarCliente(){
		 return repo.findAll();
	}
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(	"Objeto não encontrado! Id:" + 	id + ", Tipo: " + Cliente.class.getName())); //sozinho , obj é Optional<Category> , mas obj.orElse ele retorna um objeto do tipo Cliente;

	}
	
	public List<Cliente> listar() {
	
		return repo.findAll();
		}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest =  PageRequest.of(page, linesPerPage,Direction.valueOf(direction), orderBy );
		return repo.findAll(pageRequest);
	}
    
	public Cliente insert(Cliente obj) {
		
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = this.buscar(obj.getId());
		this.updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		this.buscar(id);
		try {
			
			this.repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há entidades relacionadas");
		}
	}
	
	
	public Cliente dtoToCliente(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
  
}
