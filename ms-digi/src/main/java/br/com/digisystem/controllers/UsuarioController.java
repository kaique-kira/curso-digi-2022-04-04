package br.com.digisystem.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.digisystem.Dtos.UsuarioDTO;
import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.services.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping("usuarios")
	public ResponseEntity<List<UsuarioDTO>> getAll() {
		List<UsuarioEntity> lista = this.usuarioService.getAll();
		List<UsuarioDTO> listaDTO = new ArrayList<>();
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add(lista.get(i).toDTO());
		};
	List<UsuarioDTO> ListaDTO2 = lista.stream().map(x -> x.toDTO()).collect(Collectors.toList());
	return ResponseEntity.ok().body(listaDTO);		
	}
 
	@GetMapping("usuarios/{id}")
	public ResponseEntity<UsuarioDTO> getOne(@PathVariable int id) {
		System.out.println("o valor do ID é "+ id);
		UsuarioEntity usuarioEntity =  this.usuarioService.getOne(id);
		return ResponseEntity.ok().body(usuarioEntity.toDTO());
	}
	
	@PostMapping("usuarios")
	public ResponseEntity<UsuarioDTO> create(@Valid @RequestBody UsuarioDTO usuario ) {
		UsuarioEntity usuarioEntity = usuario.toEntity();
		UsuarioEntity usuarioEntitySalvo = this.usuarioService.create(usuarioEntity);
		return ResponseEntity.ok().body(usuarioEntitySalvo.toDTO());
		}

	@PatchMapping("usuarios/{id}")
	public ResponseEntity<UsuarioDTO> update(@PathVariable int id, 
			@RequestBody UsuarioDTO usuario) {
		UsuarioEntity usuarioEntitySalvo = this.usuarioService.update(id, usuario.toEntity());
		return ResponseEntity.ok().body(usuarioEntitySalvo.toDTO());
		 
	}

	@DeleteMapping("usuarios/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {
			this.usuarioService.delete(id);
			return ResponseEntity.ok().build();
	}
	
	@GetMapping("usuarios/get-by-nome/{nome}")
	public ResponseEntity<List<UsuarioDTO>> getByNomeContains(@PathVariable String nome){
		List<UsuarioEntity> lista = this.usuarioService.getByNome(nome);
		List<UsuarioDTO> listaDTO = new ArrayList<>();
		for (int i = 0; i < lista.size();i++) {
			listaDTO.add(lista.get(i).toDTO());
		}
		return ResponseEntity.ok().body(listaDTO);
	}
		@PatchMapping("usuarios/update/{id}")
		public ResponseEntity<Void> updateUsuario(@PathVariable int id, @RequestBody UsuarioDTO dto) {
			this.usuarioRepository.updateUsario(id, dto.getNome());
			return ResponseEntity.ok().build();
		}
	}
	}
	
}