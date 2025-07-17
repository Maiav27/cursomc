package com.vinim.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinim.cursomc.domain.ItemPedido;
import com.vinim.cursomc.domain.ItemPedidoPK;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPK> {

}
