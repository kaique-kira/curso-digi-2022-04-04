package br.com.digisystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.digisystem.dtos.ProfessorDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "professores")
public class ProfessorEntity {
	
	

@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
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