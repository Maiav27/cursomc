package com.vinim.cursomc.service;



import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinim.cursomc.domain.ItemPedido;
import com.vinim.cursomc.domain.PagamentoComBoleto;
import com.vinim.cursomc.domain.Pedido;
import com.vinim.cursomc.domain.enums.EstadoPagamento;
import com.vinim.cursomc.repositories.ItemPedidoRepository;
import com.vinim.cursomc.repositories.PagamentoRepository;
import com.vinim.cursomc.repositories.PedidoRepository;
import com.vinim.cursomc.service.exceptions.ObjectNotFoundException;
@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	@Autowired 
	private BoletoService boletoService;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido buscar(Integer id) {
	   Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(	"Objeto não encontrado! Id:" + 	id + ", Tipo: " + Pedido.class.getName())); //sozinho , obj é Optional<Pedido> , mas obj.orElse ele retorna um objeto do tipo Pedido;

	}
	
	public Pedido insert(Pedido pedido) {
		pedido.setId(null);
		pedido.setInstante(new Date());
		pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
		}
		pedido = repo.save(pedido);
		pagamentoRepository.save(pedido.getPagamento());
		for (ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(pedido);
		}
		itemPedidoRepository.saveAll(pedido.getItens());
		return pedido;
	}

}
