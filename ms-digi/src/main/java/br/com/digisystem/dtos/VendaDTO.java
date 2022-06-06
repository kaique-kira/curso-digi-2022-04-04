package br.com.digisystem.dtos;

import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.VendaEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaDTO {
	private int id;
	private double valorTotal;
	
	private List<ProdutoDTO> produtos;

	
	public VendaEntity toEntity() {
		ModelMapper mapper = new ModelMapper();		
		return mapper.map(this, VendaEntity.class);
	}
}
