package br.com.itau.pontoeletronico.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;

public class UsuarioDTO {

	@NotBlank(message = "Campo nome é obrigatório")
	private String nome;

	@CPF(message = "Campo CPF inválido")
	@NotBlank(message = "Campo CPF é obrigatorio")
	private String cpf;

	@NotBlank(message = "Campo email é obrigatório")
	private String email;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
