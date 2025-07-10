package com.vinim.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinim.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
