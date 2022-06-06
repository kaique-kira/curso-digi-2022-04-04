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

import br.com.digisystem.dtos.VendaDTO;
import br.com.digisystem.entities.VendaEntity;
import br.com.digisystem.services.VendaService;

@RestController
public class VendaController {
	
	@Autowired
	private VendaService vendaService;

	//GET
	@GetMapping("vendas")
	public ResponseEntity<List<VendaDTO>> getAll() {

List<VendaEntity> lista = this.vendaService.getAll();
		
		List<VendaDTO> listaDTO = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
		
		return ResponseEntity.ok().body( listaDTO );		
	}

	//GET 1
	@GetMapping("vendas/{id}")
	public ResponseEntity<VendaDTO> getOne(@PathVariable int id) {
		System.out.println("o valor do ID é "+ id);
		
		VendaEntity vendaEntity = this.vendaService.getOne(id);
		
		return ResponseEntity.ok().body( vendaEntity.toDTO() );
	}

	
	//CREATE
	@PostMapping("vendas")
public ResponseEntity<VendaDTO> create(@RequestBody VendaDTO venda ) {
		
		

		
		VendaEntity vendaEntity = venda.toEntity();
		
		VendaEntity vendaEntitySalvo = this.vendaService.save( vendaEntity );
		
		return ResponseEntity.ok().body( vendaEntitySalvo.toDTO() );
	}

	//UPDATE
	@PatchMapping("vendas/{id}")
	public ResponseEntity<VendaDTO> update(@PathVariable int id, 
			@RequestBody VendaDTO venda) {
		
		VendaEntity vendaEntitySalvo =
				this.vendaService.update(id, venda.toEntity() );
	
	return ResponseEntity.ok().body( vendaEntitySalvo.toDTO() );
	}
	
	// DELETE
	@DeleteMapping("vendas/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {

		this.vendaService.delete(id);
		
		return ResponseEntity.ok().build();
	}
}