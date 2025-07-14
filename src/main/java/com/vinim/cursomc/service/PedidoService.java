package com.vinim.cursomc.service;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinim.cursomc.domain.Pedido;
import com.vinim.cursomc.repositories.PedidoRepository;
import com.vinim.cursomc.service.exceptions.ObjectNotFoundException;
@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
	   Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(	"Objeto não encontrado! Id:" + 	id + ", Tipo: " + Pedido.class.getName())); //sozinho , obj é Optional<Category> , mas obj.orElse ele retorna um objeto do tipo Categoria;

	}

}
