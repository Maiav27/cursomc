package com.vinim.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinim.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
