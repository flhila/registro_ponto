package br.com.itau.pontoeletronico.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.pontoeletronico.models.RegistroPonto;
import br.com.itau.pontoeletronico.models.Usuario;

public interface RegistroPontoRepository extends CrudRepository<RegistroPonto, Integer> {
	
	public List<RegistroPonto> findByUsuario(Usuario usuario);

}
