package br.com.digisystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digisystem.entities.VendaEntity;
import br.com.digisystem.repositories.VendaRepository;

@Service
public class VendaService {

	@Autowired
	private VendaRepository vendaRepository;
	
	public List<VendaEntity> getAll(){
		return this.vendaRepository.findAll();
	}
	
	public VendaEntity getOne(int id) {
		return this.vendaRepository.findById(id).orElseThrow();
	}
	
	public VendaEntity save(VendaEntity venda) {
		return this.vendaRepository.save(venda);
	}
	
	public VendaEntity update(int id, VendaEntity venda) {
		
		VendaEntity vendaUpdate = this.vendaRepository.
				findById(id).orElseThrow();
		
		vendaUpdate.setValorTotal( venda.getValorTotal() );
		vendaUpdate.setUsuario( venda.getUsuario() );
		
	
		
		return this.vendaRepository.save(vendaUpdate);
	}
	
	public void delete(int id) {
		try {
			this.vendaRepository.deleteById(id);
		}
		catch (Exception e) {
			System.out.println("Erro ao deletar o registro");
		}
		
	}
}