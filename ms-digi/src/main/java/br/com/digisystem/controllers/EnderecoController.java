package br.com.digisystem.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import br.com.digisystem.dtos.EnderecoDTO;
import br.com.digisystem.entities.EnderecoEntity;
import br.com.digisystem.services.EnderecoService;

@RestController
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;

	//GET
	@GetMapping("enderecos")
	public ResponseEntity<List<EnderecoDTO>> getAll() {

List<EnderecoEntity> lista = this.enderecoService.getAll();
		
		List<EnderecoDTO> listaDTO = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
		
		return ResponseEntity.ok().body( listaDTO );		
	}

	//GET 1
	@GetMapping("enderecos/{id}")
	public ResponseEntity<EnderecoDTO> getOne(@PathVariable int id) {
		System.out.println("o valor do ID é "+ id);
		
		EnderecoEntity enderecoEntity = this.enderecoService.getOne(id);
		
		return ResponseEntity.ok().body( enderecoEntity.toDTO() );
	}

	
	//CREATE
	@PostMapping("enderecos")
public ResponseEntity<EnderecoDTO> create(@RequestBody EnderecoDTO endereco ) {
		
		

		
		EnderecoEntity enderecoEntity = endereco.toEntity();
		
		EnderecoEntity enderecoEntitySalvo = this.enderecoService.save( enderecoEntity );
		
		return ResponseEntity.ok().body( enderecoEntitySalvo.toDTO() );
	}

	//UPDATE
	@PatchMapping("enderecos/{id}")
	public ResponseEntity<EnderecoDTO> update(@PathVariable int id, 
			@RequestBody EnderecoDTO professor) {
		
		EnderecoEntity enderecoEntitySalvo =
				this.enderecoService.update(id, professor.toEntity() );
	
	return ResponseEntity.ok().body( enderecoEntitySalvo.toDTO() );
	}
	
	// DELETE
	@DeleteMapping("enderecos/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {

		this.enderecoService.delete(id);
		
		return ResponseEntity.ok().build();
	}
}