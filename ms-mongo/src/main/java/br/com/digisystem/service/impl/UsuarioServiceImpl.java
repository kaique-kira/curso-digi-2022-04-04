package br.com.digisystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repository.CustomRepository;
import br.com.digisystem.repository.UsuarioRepository;
import br.com.digisystem.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CustomRepository customRepository;
	
	public List<UsuarioEntity> getAll() {
		List<UsuarioEntity> usuarios = this.usuarioRepository.findAll();
		return usuarios;
	}
	
	public UsuarioEntity getOne(String id) {
		return this.usuarioRepository.findById(id)
				.orElseThrow( 
						() -> new ObjNotFoundException("Elemento com ID "+ id + " não foi localizado") );
	}
	
	public UsuarioEntity create(UsuarioEntity usuario) {
		UsuarioEntity meuUsuario = new UsuarioEntity();
		meuUsuario.setNome(usuario.getNome());
		meuUsuario.setEmail(usuario.getEmail());		
		System.out.println(usuario);
		meuUsuario = this.usuarioRepository.save(meuUsuario);
		return meuUsuario;
	}
	
	public UsuarioEntity update(String id, UsuarioEntity usuario) {	
		UsuarioEntity usuarioUpdate = 
					this.usuarioRepository.findById(id)
						.orElseThrow( () -> new ObjNotFoundException("ID " + id +  " não localizado") );
			usuarioUpdate.setEmail( usuario.getEmail() );
			usuarioUpdate.setNome( usuario.getNome() );
			return this.usuarioRepository.save(usuarioUpdate);
	}
	
	public void delete(String id) {
		this.usuarioRepository.deleteById(id);
	}
	public List<UsuarioEntity> getByNome(String nome){
		return this.usuarioRepository.searchByNome(nome);	
	}
	
	public UsuarioEntity updateUsuario(String id, String nome) {
		return this.customRepository.updateUsuario(id, nome);
	}
}