package br.com.itau.pontoeletronico.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.itau.pontoeletronico.dtos.UsuarioDTO;
import br.com.itau.pontoeletronico.dtos.mapper.UsuarioMapper;
import br.com.itau.pontoeletronico.services.UsuarioService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UsuarioController.class)
public class UsuarioControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UsuarioService usuarioService;
	
	private ObjectMapper mapper = new ObjectMapper();

	private UsuarioDTO usuario;

	@Before
	public void inicializar() {
		usuario = new UsuarioDTO();
		usuario.setNome("Novo Usuario");
		usuario.setCpf("297.293.348-62");
		usuario.setEmail("email@teste.com");
	}

	@Test
	public void deveCriarUmUsuario() throws Exception {
		when(usuarioService.criarUsuario(any(UsuarioDTO.class))).thenReturn(UsuarioMapper.from(usuario));

		String usuarioJson = mapper.writeValueAsString(usuario);

		mockMvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON).content(usuarioJson))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void deveValidarCPF() throws Exception {
		usuario.setCpf("111.222.333-44");
		when(usuarioService.criarUsuario(any(UsuarioDTO.class))).thenReturn(UsuarioMapper.from(usuario));

		String usuarioJson = mapper.writeValueAsString(usuario);

		mockMvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON).content(usuarioJson))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void deveValidarCampos() throws Exception {
		usuario.setCpf("");
		usuario.setNome("");
		usuario.setEmail("");
		when(usuarioService.criarUsuario(any(UsuarioDTO.class))).thenReturn(UsuarioMapper.from(usuario));

		String usuarioJson = mapper.writeValueAsString(usuario);

		mockMvc.perform(post("/usuario").contentType(MediaType.APPLICATION_JSON).content(usuarioJson))
				.andExpect(status().isBadRequest());
	}

}
