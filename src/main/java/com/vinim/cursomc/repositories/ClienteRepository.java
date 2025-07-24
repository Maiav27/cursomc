package com.vinim.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vinim.cursomc.domain.Cliente;

import jakarta.transaction.Transactional;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	
 Cliente findByEmail(String email);

}
