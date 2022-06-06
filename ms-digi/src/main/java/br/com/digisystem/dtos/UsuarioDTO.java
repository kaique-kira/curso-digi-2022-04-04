package br.com.digisystem.dtos;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import br.com.digisystem.entities.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private int id;
	
	@NotEmpty( message = "O campo nome é obrigatório" )
	@NotBlank( message = "o campo nome não pode ser vazio")
	@Length(min = 3, message = "O campo nome deve possuir pelo menos 3 caracteres")
	private String nome;
	
	private String email;
	
	private EnderecoDTO endereco;
	
	private List<VendaDTO> vendas;
	
	public UsuarioEntity toEntity() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, UsuarioEntity.class);
	}
	
}