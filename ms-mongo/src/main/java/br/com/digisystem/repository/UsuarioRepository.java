package br.com.digisystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.digisystem.entities.UsuarioEntity;

public interface UsuarioRepository extends MongoRepository<UsuarioEntity, String> {

}
