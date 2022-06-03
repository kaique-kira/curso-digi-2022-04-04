package br.com.digisystem.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.digisystem.Dtos.VendaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="vendas")
public class VendaEntity {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "valor_total")
	private double valorTotal;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private UsuarioEntity usuario;
	
	@ManyToMany(mappedBy = "vendas")
	private List<ProdutoEntity> produtos;
	
	public VendaDTO toDTO() {
		ModelMapper mapper = new ModelMapper();
		
		return mapper.map(this, VendaDTO.class);
	}
	
}

