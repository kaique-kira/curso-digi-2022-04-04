package br.com.digisystem.Dtos;

import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.ProdutoEntity;

public class ProdutoDTO {
	private int id;
	private double valor;
	private String nome;
	private String descricao;
	
	public ProdutoEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(this, ProdutoEntity.class);
	}
	

}
