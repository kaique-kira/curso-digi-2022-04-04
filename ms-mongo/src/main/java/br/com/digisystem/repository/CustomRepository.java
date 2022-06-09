package br.com.digisystem.repository;

import br.com.digisystem.entities.UsuarioEntity;

public interface CustomRepository {

	UsuarioEntity updateUsuario(String id, String nome);
}