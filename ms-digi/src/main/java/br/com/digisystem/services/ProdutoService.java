package br.com.digisystem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digisystem.entities.ProdutoEntity;
import br.com.digisystem.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<ProdutoEntity> getAll(){
		return this.produtoRepository.findAll();
	}
	
	public ProdutoEntity getOne(int id) {
		return this.produtoRepository.findById(id).orElseThrow();
	}
	
	public ProdutoEntity save(ProdutoEntity produto) {
		return this.produtoRepository.save(produto);
	}
	
	public ProdutoEntity update(int id, ProdutoEntity produto) {
		
		ProdutoEntity produtoUpdate = this.produtoRepository.
				findById(id).orElseThrow();
		
		produtoUpdate.setValor( produto.getValor() );
		produtoUpdate.setNome( produto.getNome() );
		produtoUpdate.setDescricao( produto.getDescricao() );
		
	
		
		return this.produtoRepository.save(produtoUpdate);
	}
	
	public void delete(int id) {
		try {
			this.produtoRepository.deleteById(id);
		}
		catch (Exception e) {
			System.out.println("Erro ao deletar o registro");
		}
		
	}
}