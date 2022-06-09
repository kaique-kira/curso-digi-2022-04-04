package br.com.digisystem.entities;



import org.modelmapper.ModelMapper;
import org.springframework.data.annotation.Id;

import br.com.digisystem.dtos.ProfessorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfessorEntity {
	
	

	@Id
	private int id;

	private String nome;
	private String cpf;
	private String telefone;
	private String email;
	
	
public ProfessorDTO toDTO() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, ProfessorDTO.class);
	}
	
}