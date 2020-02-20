package br.com.itau.pontoeletronico.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.itau.pontoeletronico.dtos.UsuarioDTO;
import br.com.itau.pontoeletronico.exceptions.ValidacaoException;
import br.com.itau.pontoeletronico.models.Usuario;
import br.com.itau.pontoeletronico.presenter.UsuarioPresenter;
import br.com.itau.pontoeletronico.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public UsuarioPresenter buscar(@PathVariable Integer id) {
		return usuarioService.findById(id);
	}

	@GetMapping
	public List<UsuarioPresenter> listarUsuarios() {
		return usuarioService.listAll();
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Usuario criarUsuario(@Valid @RequestBody UsuarioDTO usuario) {
		return usuarioService.criarUsuario(usuario);
	}

	@PatchMapping("/{id}")
	public void alterarUsuario(@PathVariable Integer id, @Valid @RequestBody UsuarioDTO usuarioDto) {
		try {
			usuarioService.alterarUsuario(id, usuarioDto);
		} catch (ValidacaoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}	
	}

}
