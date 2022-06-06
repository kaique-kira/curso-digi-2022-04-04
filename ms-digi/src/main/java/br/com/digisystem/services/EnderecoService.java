package br.com.digisystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digisystem.entities.EnderecoEntity;
import br.com.digisystem.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<EnderecoEntity> getAll(){
		return this.enderecoRepository.findAll();
	}
	
	public EnderecoEntity getOne(int id) {
		return this.enderecoRepository.findById(id).orElseThrow();
	}
	
	public EnderecoEntity save(EnderecoEntity endereco) {
		return this.enderecoRepository.save(endereco);
	}
	
	public EnderecoEntity update(int id, EnderecoEntity endereco) {
		
		EnderecoEntity enderecoUpdate = this.enderecoRepository.
				findById(id).orElseThrow();
		
		enderecoUpdate.setLogradouro( endereco.getLogradouro() );
		enderecoUpdate.setNumero( endereco.getNumero() );
		enderecoUpdate.setCep( endereco.getCep() );
		enderecoUpdate.setComplemento(endereco.getComplemento() );
		enderecoUpdate.setBairro(endereco.getBairro() );
		enderecoUpdate.setUf(endereco.getUf() );
		
		return this.enderecoRepository.save(enderecoUpdate);
	}
	
	public void delete(int id) {
		try {
			this.enderecoRepository.deleteById(id);
		}
		catch (Exception e) {
			System.out.println("Erro ao deletar o registro");
		}
		
	}
}