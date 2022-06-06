package br.com.digisystem.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.digisystem.dtos.UsuarioDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
	
	

//	public UsuarioEntity(int id, String nome, String email) {
//		super();
//		this.id = id;
//		this.nome = nome;
//		this.email = email;
//	}

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String nome;
	
	private String email;
	
	@OneToOne
	@JoinColumn(name= "endereco_id")
	private EnderecoEntity endereco;
	
	@OneToMany(mappedBy = "usuario")
	private List<VendaEntity> vendas;
	
	
public UsuarioDTO toDTO() {
		
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, UsuarioDTO.class);
	}
	
//	public String toString() {
//		return "olá";
//		GSON	
//	}
}