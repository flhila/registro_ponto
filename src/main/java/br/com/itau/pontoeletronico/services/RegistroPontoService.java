package br.com.itau.pontoeletronico.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.pontoeletronico.dtos.RegistroPontoDTO;
import br.com.itau.pontoeletronico.dtos.mapper.RegistroPontoMapper;
import br.com.itau.pontoeletronico.exceptions.ValidacaoException;
import br.com.itau.pontoeletronico.models.RegistroPonto;
import br.com.itau.pontoeletronico.models.Usuario;
import br.com.itau.pontoeletronico.presenter.RegistrosPontoUsuarioPresenter;
import br.com.itau.pontoeletronico.repositories.RegistroPontoRepository;
import br.com.itau.pontoeletronico.repositories.UsuarioRepository;

@Service
public class RegistroPontoService {

	@Autowired
	private RegistroPontoRepository registroPontoRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public RegistrosPontoUsuarioPresenter listarPorUsuario(Integer idUsuario) {
		Optional<Usuario> usuario = null;
		
		usuario = usuarioRepository.findById(idUsuario);
		
		if (!usuario.isPresent()) {
			throw new ValidacaoException("usuario.id", "Usuário não cadastrado.");
		}
		
		return new RegistrosPontoUsuarioPresenter(registroPontoRepository.findByUsuario(usuario.get()));
	}
	
	public RegistroPonto registrarPonto(RegistroPontoDTO registroPontoDto) {
		
		Optional<Usuario> usuario = null;
		
		if (registroPontoDto.getUsuarioId() == null || registroPontoDto.getUsuarioId() == 0) {
			throw new ValidacaoException("usuario.id", "Dados do usuário são obrigatórios ao Registrar Ponto.");
		} else {
			usuario = usuarioRepository.findById(registroPontoDto.getUsuarioId());
			
			if (!usuario.isPresent()) {
				throw new ValidacaoException("usuario.id", "Dados do usuário são obrigatórios ao Registrar Ponto.");
			}
		}
		
		RegistroPonto registroPonto = RegistroPontoMapper.from(registroPontoDto);
		registroPonto.setUsuario(usuario.get());
		return registroPontoRepository.save(registroPonto);
	}
}
