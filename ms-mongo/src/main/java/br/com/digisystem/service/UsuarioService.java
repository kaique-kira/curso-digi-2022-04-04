package br.com.digisystem.service;

import java.util.List;

import br.com.digisystem.entities.UsuarioEntity;

public interface UsuarioService {
	List<UsuarioEntity> getAll();
	UsuarioEntity getOne(String id);
	UsuarioEntity create(UsuarioEntity usuario);
	UsuarioEntity update(String id, UsuarioEntity usuario);
	void delete(String id);
	List<UsuarioEntity> getByNome(String nome);
	UsuarioEntity updateUsuario(String id, String nome);

}
