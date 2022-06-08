package br.com.digisystem.repository;

import java.util.List;

import org.springframework.data.mongodb.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.ProfessorEntity;

@Repository
public interface ProfessorRepository extends MongoRepository<ProfessorEntity, Integer> {

//	// SELECT * FROM USUARIO WHERE NOME LIKE '%<nome>%'
//	public List<ProfessorEntity> findByNomeContains(String nome);
//	
//	// JPQL
//	@Query("SELECT u FROM UsuarioEntity u WHERE u.nome LIKE %:nome%")
//	public List<ProfessorEntity> searchByNome(@Param("nome") String nome);
//	
//	@Query(value="SELECT * FROM USUARIOS WHERE nome LIKE %:nome%",
//			nativeQuery = true)
//	public List<ProfessorEntity> searchByNomeNativo(@Param("nome") String nome);
//	
//	// SELECT * FROM USUARIO WHERE NOME LIKE '%<nome>'
//	public List<ProfessorEntity> findByNomeStartsWith(String nome);
//	
//	// SELECT * FROM USUARIO WHERE NOME LIKE '<nome>%'
//	public List<ProfessorEntity> findByNomeEndsWith(String nome);
//	
//	// SELECT * FROM USUARIO WHERE NOME LIKE '%<nome>%' AND email LIKE '%<email>%'
//	public List<ProfessorEntity> findByNomeContainsAndEmailContains(String nome, String email);
//	
//	@Modifying
//	@Query(value = "UPDATE USUARIOS SET NOME = :nome WHERE ID = :id",
//			nativeQuery = true)
//	public void updateProfessor(@Param("id")int id, @Param("nome") String nome);
	
	
	
}