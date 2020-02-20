package br.com.itau.pontoeletronico.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.itau.pontoeletronico.dtos.RegistroPontoDTO;
import br.com.itau.pontoeletronico.exceptions.ValidacaoException;
import br.com.itau.pontoeletronico.models.RegistroPonto;
import br.com.itau.pontoeletronico.presenter.RegistrosPontoUsuarioPresenter;
import br.com.itau.pontoeletronico.services.RegistroPontoService;

@RestController
@RequestMapping("/registro")
public class RegistroPontoController {
	
	@Autowired
	private RegistroPontoService service;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public RegistroPonto registrarPonto(@Valid @RequestBody RegistroPontoDTO registroPontoDto) {
		try {
			return service.registrarPonto(registroPontoDto);			
		} catch (ValidacaoException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public RegistrosPontoUsuarioPresenter listarTodosPorUsuario(@PathVariable Integer id) {
		return service.listarPorUsuario(id);
	}
}
