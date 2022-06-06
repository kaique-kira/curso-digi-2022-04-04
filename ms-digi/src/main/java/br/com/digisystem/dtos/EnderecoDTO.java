package br.com.digisystem.dtos;

import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.EnderecoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoDTO {

	private int id;
	
	private String logradouro;
	private String numero;
	private String complemento;
	private String cep;
	private String bairro;
	private String uf;
	
	public EnderecoEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, EnderecoEntity.class);
	}
}