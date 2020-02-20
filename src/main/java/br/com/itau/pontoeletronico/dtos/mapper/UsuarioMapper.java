package br.com.itau.pontoeletronico.dtos.mapper;

import java.time.LocalDateTime;

import br.com.itau.pontoeletronico.dtos.UsuarioDTO;
import br.com.itau.pontoeletronico.models.Usuario;

public class UsuarioMapper {
	
	public static Usuario from(UsuarioDTO usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioDto.getNome());
		usuario.setCpf(usuarioDto.getCpf());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setDataCadastro(LocalDateTime.now());
		return usuario;
	}
	
	public static void from(Usuario usuario, UsuarioDTO usuarioDto) {
		usuario.setNome(usuarioDto.getNome());
		usuario.setCpf(usuarioDto.getCpf());
		usuario.setEmail(usuarioDto.getEmail());
	}

}
