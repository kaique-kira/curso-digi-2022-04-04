package br.com.digisystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
	public List<UsuarioEntity> findAll();
	
	public List<UsuarioEntity> findByNomeContains(String nome);
	
	@Query("select u from UsuarioEntity u where u.nome like %:nome%")
	public List<UsuarioEntity> searchByNome(@Param("nome") String nome);
	
	@Query(value = "Select * from usuarios where u.nome like%:nome%", nativeQuery = true)
	public List<UsuarioEntity> SearchByNomeNativo(@Param("nome") String nome);
	
	public List<UsuarioEntity> findByNomeStartsWith(String nome);
	
	public List<UsuarioEntity> findByNomeEndsWith(String nome);
	
	public List<UsuarioEntity> findByNomeContainsAndEmailContains(String nome, String email);
	
	@Modifying
	@Query(value="update usuarios set nome = :nome where id = :id", nativeQuery = true)
	public void updateUsario (@Param("id")int id, @Param("nome")String nome);
	

}
