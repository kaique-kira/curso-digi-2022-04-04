package br.com.digisystem.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
//	private ArrayList<UsuarioEntity> listaUsuario = new ArrayList<>();
//	private int contador = 1;
	
	public List<UsuarioEntity> getAll() {
		
		List<UsuarioEntity> usuarios = this.usuarioRepository.findAll();
		return usuarios;
	}
	
	public UsuarioEntity getOne(int id) {
			
		return this.usuarioRepository.findById(id)
				.orElseThrow( ()-> new ObjNotFoundException("Elemento com ID "+id+" não encontrado"));
		
		//if (usuarioOptional.isPresent()) {
		//	return usuarioOptional.get();
		//}
		//else {
		//	return null;
		//}
		
		
	}
	
	public UsuarioEntity create(UsuarioEntity usuario) {
		
		UsuarioEntity meuUsuario= new UsuarioEntity();
		
		//meuUsuario.setId(this.contador);
		//meuUsuario.setNome("Fabrizio " + this.contador);
		meuUsuario.setNome(usuario.getNome());
		//meuUsuario.setEmail("fabrizio@grandeporte.com.br");
		meuUsuario.setEmail(usuario.getEmail());
		
		System.out.println(usuario);
		
		//listaUsuario.add(meuUsuario);
		
		meuUsuario = this.usuarioRepository.save(meuUsuario);
		
		//this.contador++;
		
		
		return meuUsuario;
	}
	
	public UsuarioEntity update(int id, UsuarioEntity usuario) {
		
//		for (int i =0 ; i < this.listaUsuario.size(); i++) {
//			
//			if (this.listaUsuario.get(i).getId() == id) {
//				
//				//if (usuario.getNome() != null ) {
//				this.listaUsuario.get(i).setNome( usuario.getNome() );
//				//}
//				
//				this.listaUsuario.get(i).setEmail( usuario.getEmail() ); 
//				
//				return this.listaUsuario.get(i);
//			}
//		}
//		
//		return null;
		
		Optional<UsuarioEntity> usuarioOptional = this.usuarioRepository.findById(id);
	
		if (usuarioOptional.isPresent()) {
			UsuarioEntity usuarioUpdate = usuarioOptional.get();
			
			usuarioUpdate.setEmail(usuario.getEmail() );
			usuarioUpdate.setNome(usuario.getNome() );
			
			return this.usuarioRepository.save(usuarioUpdate);
			}
		else {
			return null;
		}
	}
	
	public void delete(int id) {
		
//		for (int i = 0; i < this.listaUsuario.size(); i++) {
//			if (this.listaUsuario.get(i).getId() == id) {
//				this.listaUsuario.remove(i);
//			}
//		}
		this.usuarioRepository.deleteById(id);	
	}
	
	public List<UsuarioEntity> getByNome(String nome){
		//return this.usuarioRepository.findByNomeContains(nome);
		return this.usuarioRepository.searchByNomeNativo(nome);
	}
	
	@Transactional
	public void updateUsuario(int id, String nome) {
		this.usuarioRepository.updateUsuario(id, nome);
	}
}