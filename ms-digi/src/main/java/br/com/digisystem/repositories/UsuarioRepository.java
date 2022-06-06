package br.com.digisystem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

	// SELECT * FROM USUARIO WHERE NOME LIKE '%<nome>%'
	public List<UsuarioEntity> findByNomeContains(String nome);
	
	// JPQL
	@Query("SELECT u FROM UsuarioEntity u WHERE u.nome LIKE %:nome%")
	public List<UsuarioEntity> searchByNome(@Param("nome") String nome);
	
	@Query(value="SELECT * FROM USUARIOS WHERE nome LIKE %:nome%",
			nativeQuery = true)
	public List<UsuarioEntity> searchByNomeNativo(@Param("nome") String nome);
	
	// SELECT * FROM USUARIO WHERE NOME LIKE '%<nome>'
	public List<UsuarioEntity> findByNomeStartsWith(String nome);
	
	// SELECT * FROM USUARIO WHERE NOME LIKE '<nome>%'
	public List<UsuarioEntity> findByNomeEndsWith(String nome);
	
	// SELECT * FROM USUARIO WHERE NOME LIKE '%<nome>%' AND email LIKE '%<email>%'
	public List<UsuarioEntity> findByNomeContainsAndEmailContains(String nome, String email);
	
	@Modifying
	@Query(value = "UPDATE USUARIOS SET NOME = :nome WHERE ID = :id",
			nativeQuery = true)
	public void updateUsuario(@Param("id")int id, @Param("nome") String nome);
	
	
	
}