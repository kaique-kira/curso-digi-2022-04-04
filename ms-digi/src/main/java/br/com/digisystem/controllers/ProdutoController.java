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

import br.com.digisystem.dtos.ProdutoDTO;
import br.com.digisystem.entities.ProdutoEntity;
import br.com.digisystem.services.ProdutoService;

@RestController
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;

	//GET
	@GetMapping("produtos")
	public ResponseEntity<List<ProdutoDTO>> getAll() {

List<ProdutoEntity> lista = this.produtoService.getAll();
		
		List<ProdutoDTO> listaDTO = new ArrayList<>();
		
		for (int i = 0; i < lista.size(); i++) {
			listaDTO.add( lista.get(i).toDTO() );
		}
		
		return ResponseEntity.ok().body( listaDTO );		
	}

	//GET 1
	@GetMapping("produtos/{id}")
	public ResponseEntity<ProdutoDTO> getOne(@PathVariable int id) {
		System.out.println("o valor do ID é "+ id);
		
		ProdutoEntity produtoEntity = this.produtoService.getOne(id);
		
		return ResponseEntity.ok().body( produtoEntity.toDTO() );
	}

	
	//CREATE
	@PostMapping("produtos")
public ResponseEntity<ProdutoDTO> create(@RequestBody ProdutoDTO produto ) {
		
		

		
		ProdutoEntity produtoEntity = produto.toEntity();
		
		ProdutoEntity produtoEntitySalvo = this.produtoService.save( produtoEntity );
		
		return ResponseEntity.ok().body( produtoEntitySalvo.toDTO() );
	}

	//UPDATE
	@PatchMapping("produtos/{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable int id, 
			@RequestBody ProdutoDTO produto) {
		
		ProdutoEntity produtoEntitySalvo =
				this.produtoService.update(id, produto.toEntity() );
	
	return ResponseEntity.ok().body( produtoEntitySalvo.toDTO() );
	}
	
	// DELETE
	@DeleteMapping("produtos/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) {

		this.produtoService.delete(id);
		
		return ResponseEntity.ok().build();
	}
}