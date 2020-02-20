package br.com.itau.pontoeletronico.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.itau.pontoeletronico.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

}
