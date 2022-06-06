package br.com.digisystem.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.digisystem.dtos.EnderecoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "enderecos")
public class EnderecoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String logradouro;
	private String numero;
	private String cep;
	private String complemento;
	private String bairro;
	private String uf;
	
	@OneToOne(mappedBy="endereco")
	private UsuarioEntity usuario;
	
	public EnderecoDTO toDTO() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this,EnderecoDTO.class);
	}

}