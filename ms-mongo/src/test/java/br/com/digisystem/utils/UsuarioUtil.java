package br.com.digisystem.utils;

import br.com.digisystem.entities.UsuarioEntity;

public class UsuarioUtil {

	protected UsuarioEntity createUsuarioValid() {
		
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		
		usuarioEntity.setEmail("email");
		usuarioEntity.setNome("nome");
		
		return usuarioEntity;
		
	}

}