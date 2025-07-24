package com.vinim.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.vinim.cursomc.domain.Cliente;
import com.vinim.cursomc.service.validation.ClienteUpdate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@ClienteUpdate
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min=5, max=120 ,message="O tamanho deve ter entre 5 e 120 caracteres")
	private String nome;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "email inválido")
	private String email;

	public ClienteDTO() {

	}

	public ClienteDTO(Cliente cliente) {
		super();
		this.id = cliente.getId();
		
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
