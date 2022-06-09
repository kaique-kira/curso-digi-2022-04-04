package br.com.digisystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import br.com.digisystem.entities.UsuarioEntity;

public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {

	@Query("  { nome: {$regex: /?0/  }  }  ")
	public List<UsuarioEntity> searchByNomeNativo(String nome);
	
	@Query("  { nome: {$regex: /:#{#nome}/  }  }  ")
	public List<UsuarioEntity> searchByNome(@Param("nome") String nome);
}
