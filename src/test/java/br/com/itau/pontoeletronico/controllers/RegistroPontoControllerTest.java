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

import br.com.itau.pontoeletronico.dtos.RegistroPontoDTO;
import br.com.itau.pontoeletronico.dtos.mapper.RegistroPontoMapper;
import br.com.itau.pontoeletronico.exceptions.ValidacaoException;
import br.com.itau.pontoeletronico.models.TipoRegistro;
import br.com.itau.pontoeletronico.services.RegistroPontoService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RegistroPontoController.class)
public class RegistroPontoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private RegistroPontoService registroPontoService;
	
	private ObjectMapper mapper = new ObjectMapper();

	private RegistroPontoDTO registroPonto;

	@Before
	public void inicializar() {
		registroPonto = new RegistroPontoDTO();
		registroPonto.setTipoRegistro(TipoRegistro.ENTRADA);
		registroPonto.setUsuarioId(1);
	}

	@Test
	public void deveCriarUmRegistro() throws Exception {
		when(registroPontoService.registrarPonto(any(RegistroPontoDTO.class))).thenReturn(RegistroPontoMapper.from(registroPonto));

		String registroPontoJson = mapper.writeValueAsString(registroPonto);

		mockMvc.perform(post("/registro").contentType(MediaType.APPLICATION_JSON).content(registroPontoJson))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void deveValidarUsuario() throws Exception {
		when(registroPontoService.registrarPonto(any(RegistroPontoDTO.class))).thenThrow(ValidacaoException.class);

		String registroPontoJson = mapper.writeValueAsString(registroPonto);

		mockMvc.perform(post("/registro").contentType(MediaType.APPLICATION_JSON).content(registroPontoJson))
				.andExpect(status().isBadRequest());
	}
	
	

}
