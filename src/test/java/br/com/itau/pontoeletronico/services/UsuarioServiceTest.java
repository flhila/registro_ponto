package br.com.itau.pontoeletronico.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.itau.pontoeletronico.dtos.UsuarioDTO;
import br.com.itau.pontoeletronico.dtos.mapper.UsuarioMapper;
import br.com.itau.pontoeletronico.models.Usuario;
import br.com.itau.pontoeletronico.repositories.UsuarioRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = UsuarioService.class)
public class UsuarioServiceTest {
	
	@Autowired
	private UsuarioService usuarioService;

	@MockBean
	private UsuarioRepository usuarioRepository;
	
	private UsuarioDTO usuarioDto;
	
	private Usuario usuario;
	
	@Before
	public void inicializar() {
		usuarioDto = new UsuarioDTO();
		usuarioDto.setNome("Novo Usuario");
		usuarioDto.setCpf("297.293.348-62");
		usuarioDto.setEmail("email@teste.com");
		
		usuario = UsuarioMapper.from(usuarioDto);
	}
	
	@Test
	public void deveCriarUmUsuario() {
		when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
		
		Usuario novousuario = usuarioService.criarUsuario(usuarioDto);
		
		assertEquals(usuario.getNome(), novousuario.getNome());
	}
}
