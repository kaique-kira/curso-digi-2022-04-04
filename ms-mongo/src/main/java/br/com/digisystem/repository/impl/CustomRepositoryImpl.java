package br.com.digisystem.repository.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.repository.CustomRepository;
@Primary
@Repository
public class CustomRepositoryImpl implements CustomRepository {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Override
	public UsuarioEntity updateUsuario(String id, String nome) {
		
		// criando critério de busca
		Query query = new Query();
		
		// { nome: <nome> } 
		query.addCriteria(Criteria.where("id").is(id));
		
		// criando objeto com aributos a serem modificados
		Update update = new Update();
		update.set("nome", nome);
		
		// modificando o documento
		
		//mongoTemplate.update(UsuarioEntity.class).matching(query).apply(update);
		mongoTemplate.findAndModify(query,update,UsuarioEntity.class);
		
		// buscando o documento modificado 
		UsuarioEntity usuario = mongoTemplate.findOne(query, UsuarioEntity.class);
		
		return usuario;
	}

}