package br.com.digisystem.dtos;

import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.ProfessorEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO {
	
	private int id;
	
	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	
	public ProfessorEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProfessorEntity.class);
	}

}