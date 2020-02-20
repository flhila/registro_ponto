package br.com.itau.pontoeletronico.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.itau.pontoeletronico.dtos.RegistroPontoDTO;
import br.com.itau.pontoeletronico.dtos.mapper.RegistroPontoMapper;
import br.com.itau.pontoeletronico.exceptions.ValidacaoException;
import br.com.itau.pontoeletronico.models.RegistroPonto;
import br.com.itau.pontoeletronico.models.TipoRegistro;
import br.com.itau.pontoeletronico.models.Usuario;
import br.com.itau.pontoeletronico.presenter.RegistrosPontoUsuarioPresenter;
import br.com.itau.pontoeletronico.repositories.RegistroPontoRepository;
import br.com.itau.pontoeletronico.repositories.UsuarioRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RegistroPontoService.class)
public class RegistroPontoServiceTest {
	
	@Autowired
	private RegistroPontoService registroPontoService;
	
	@MockBean
	private RegistroPontoRepository registroPontoRepository;
	
	@MockBean
	private UsuarioRepository usuarioRepository;
	
	private RegistroPontoDTO registroPontoDto;
	private RegistroPonto registroPonto;
	private Optional<Usuario> usuario;
	
	@Before
	public void inicializar() {
		
		usuario = Optional.of(new Usuario());
		usuario.get().setId(1);
		usuario.get().setNome("Nome Usuario");
		usuario.get().setCpf("000.000.000-00");
		usuario.get().setEmail("teste@teste.com");
		
		registroPontoDto = new RegistroPontoDTO();
		registroPontoDto.setTipoRegistro(TipoRegistro.ENTRADA);
		registroPontoDto.setUsuarioId(usuario.get().getId());
		
		registroPonto = RegistroPontoMapper.from(registroPontoDto);
		registroPonto.setId(10);
		registroPonto.setUsuario(usuario.get());
	}
	
	@Test
	public void deveRegistrarPonto() {
		when(registroPontoRepository.save(any(RegistroPonto.class))).thenReturn(registroPonto);
		when(usuarioRepository.findById(any(Integer.class))).thenReturn(usuario);
		
		RegistroPonto novoregistro = registroPontoService.registrarPonto(registroPontoDto);
		
		assertEquals(registroPonto.getId(), novoregistro.getId());
	}
	
	@Test(expected = ValidacaoException.class)
	public void deveValidarUsuario() {
		when(registroPontoRepository.save(any(RegistroPonto.class))).thenReturn(registroPonto);
		when(usuarioRepository.findById(any(Integer.class))).thenReturn(Optional.of(new Usuario()));
		
		registroPontoDto.setUsuarioId(0);
		registroPontoService.registrarPonto(registroPontoDto);
	}
	
	@Test
	public void deveListarRegistrosPorUsuario() {
		
		when(registroPontoRepository.findByUsuario(usuario.get())).thenReturn(Lists.list(registroPonto));
		when(usuarioRepository.findById(any(Integer.class))).thenReturn(usuario);
	    
	    RegistrosPontoUsuarioPresenter presenter = registroPontoService.listarPorUsuario(usuario.get().getId());
	    
	    assertEquals(1, presenter.getRegistros().size());   
	}

}
