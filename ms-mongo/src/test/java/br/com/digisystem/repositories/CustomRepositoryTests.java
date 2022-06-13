package br.com.digisystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.repository.CustomRepository;
import br.com.digisystem.repository.UsuarioRepository;
import br.com.digisystem.repository.impl.CustomRepositoryImpl;
import br.com.digisystem.utils.UsuarioUtil;
import lombok.extern.slf4j.Slf4j;


@Import(CustomRepositoryImpl.class)
@DataMongoTest
@ExtendWith(SpringExtension.class)
@Slf4j
public class CustomRepositoryTests extends UsuarioUtil{
	
	@Autowired
	private CustomRepository customRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@BeforeEach
	void BeforeEachTest() {
		mongoTemplate.dropCollection(UsuarioEntity.class);
		mongoTemplate.createCollection(UsuarioEntity.class);
		log.info("Before Each");
	}
		
	// https://stackoverflow.com/questions/52857963/how-to-test-method-from-repository-which-marked-as-modifying
	@Test()
	void updateUsuario() {
		
		
		UsuarioEntity usuarioEntity = createUsuarioValid();
		
		// inserir registro no banco
		// Insiro o objeto no banco H2
		mongoTemplate.insert(usuarioEntity);
		
		// execução do teste

		customRepository.updateUsuario(usuarioEntity.getId(), "nome-alterado");
		
		/* limpando o cache do banco de dados para forçar o 
		 * teste a pegar os dados do banco de dados*/
//		testEntityManager.clear();
		
		// verificando se deu certo
		
		// buscar o item alterado no banco e verificar se o nome foi alterado
		
		Optional<UsuarioEntity> usuario = usuarioRepository
				.findById( usuarioEntity.getId() ); 
		
		assertThat( usuario ).isPresent();
		assertThat(usuario.get().getNome()).isEqualTo("nome-alterado");
	}

}