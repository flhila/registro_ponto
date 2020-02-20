package br.com.itau.pontoeletronico.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.itau.pontoeletronico.dtos.UsuarioDTO;
import br.com.itau.pontoeletronico.dtos.mapper.UsuarioMapper;
import br.com.itau.pontoeletronico.exceptions.ValidacaoException;
import br.com.itau.pontoeletronico.models.Usuario;
import br.com.itau.pontoeletronico.presenter.UsuarioPresenter;
import br.com.itau.pontoeletronico.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioPresenter findById(Integer id) {
		return new UsuarioPresenter(usuarioRepository.findById(id).get());
	}

	public List<UsuarioPresenter> listAll() {
		
		List<UsuarioPresenter> usuariosList = new ArrayList<UsuarioPresenter>();
		
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		
		for (Usuario usuario : usuarios) {
			usuariosList.add(new UsuarioPresenter(usuario));
		}
		
		return usuariosList;
	}

	public Usuario criarUsuario(UsuarioDTO usuarioDto) {
		Usuario usuario = UsuarioMapper.from(usuarioDto);
		return usuarioRepository.save(usuario);
	}
	
	public Usuario alterarUsuario(Integer id, UsuarioDTO usuarioDto) {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

		if (!usuarioOptional.isPresent()) {
			throw new ValidacaoException("id", "Usuário não localizado com esse ID");
		}
		
		Usuario usuario = usuarioOptional.get();
		UsuarioMapper.from(usuario, usuarioDto);
		
		return usuarioRepository.save(usuario);
	}
}
