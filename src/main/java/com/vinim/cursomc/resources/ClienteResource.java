package com.vinim.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vinim.cursomc.domain.Cliente;
import com.vinim.cursomc.dto.ClienteDTO;
import com.vinim.cursomc.dto.ClienteNewDTO;
import com.vinim.cursomc.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	
	@GetMapping(value="{id}")
	public  ResponseEntity<Cliente> find( @PathVariable Integer id){
			Cliente obj = service.buscar(id);
			return ResponseEntity.ok().body(obj);
	}
	

	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar(){
	List<Cliente> list = this.service.listar();
	List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
	return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping(value="/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page  ,
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy , 
			@RequestParam(value="direction", defaultValue="ASC") String direction
			) {
		Page<Cliente> list = this.service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDto);
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		Cliente obj = this.service.dtoToCliente(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
     @PostMapping
     public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO){
    	 Cliente obj = this.service.dtoToCliente(objDTO);
    	 obj = this.service.insert(obj);
    	 URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
    			 .path("/{id}").buildAndExpand(obj.getId()).toUri();
    	 return ResponseEntity.created(uri).build();
     }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		this.service.delete(id);
			
  	return ResponseEntity.noContent().build();
		
	}
	


}
