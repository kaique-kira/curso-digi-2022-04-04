package br.com.digisystem.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.websocket.server.PathParam;

import br.com.digisystem.Dtos.UsuarioDTO;
import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	private ArrayList<UsuarioEntity> listaUsuario = new ArrayList<>();
	
	public List<UsuarioEntity> getAll() {
		List<UsuarioEntity> usuario = this.usuarioRepository.findAll();
		return usuario;
	}
	
	public UsuarioEntity getOne(int id) {
		return this.usuarioRepository.findById(id).orElseThrow( () -> new ObjNotFoundException("Elemento não localizado"));
	}
	
public UsuarioEntity create(UsuarioEntity usuario) {
		
		UsuarioEntity meuUsuario = new UsuarioEntity();
		meuUsuario.setNome(usuario.getNome());
		meuUsuario.setEmail(usuario.getEmail());

		System.out.println(usuario);
		meuUsuario = this.usuarioRepository.save(meuUsuario);		
		return meuUsuario;
	}
	
public UsuarioEntity update(int id, UsuarioEntity usuario) {

	Optional<UsuarioEntity> usuarioOptional = this.usuarioRepository.findById(id);
	if (usuarioOptional.isPresent()) {
		UsuarioEntity usuarioUpdate = usuarioOptional.get();
		usuarioUpdate.setEmail( usuario.getEmail() );
		usuarioUpdate.setNome( usuario.getNome() );
		return this.usuarioRepository.save(usuarioUpdate);
	}
	else {
		return null;
	}
}
	
	public void delete(int id) {
		this.usuarioRepository.deleteById(id);
}
	
	public List<UsuarioEntity> getByNome(String nome){
		//return this.usuarioRepository.findByNomeContains(nome);
		//return this.usuarioRepository.searchByNome(nome);
		return this.usuarioRepository.SearchByNomeNativo(nome);
	}}
//	@PatchMapping("usuarios/update/{id}")
//	public ResponseEntity<Void> updateUsuario(@PathVariable int id, @RequestBody UsuarioDTO dto) {
//		this.usuarioRepository.updateUsario(id, dto.getNome());
//		return ResponseEntity.ok().build();
//	}
//}
//	
	@Transactional
	public void updateUsuario(int id, String nome) {
		this.usuarioRepository.updateUsario(id, nome);
	}
