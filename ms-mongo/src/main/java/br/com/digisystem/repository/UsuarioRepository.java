package br.com.digisystem.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.digisystem.entities.UsuarioEntity;

public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {

	@Query("  { nome: {$regex: /?0/  }  }  ")
	public List<UsuarioEntity> searchByNomeNativo(String nome);
}
