package br.com.digisystem.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.utils.UsuarioUtil;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class UsuarioRepositoryTests extends UsuarioUtil {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	void findAllTests() {
		// crio o objeto a ser inserido no H2
//		UsuarioEntity usuarioEntity = new UsuarioEntity();
//		usuarioEntity.setEmail("email");
//		usuarioEntity.setNome("nome");
		
		UsuarioEntity usuarioEntity = createUsuarioValid();
		
		// Insiro o objeto no banco H2
		testEntityManager.persist(usuarioEntity);
		
		//realizar o teste
		
		List<UsuarioEntity> lista = usuarioRepository.findAll();
		
		assertThat( lista.size() ).isEqualTo(1);
	}
	
	@Test
	void findByIdWhenFoundUser() {
		
		UsuarioEntity usuarioEntity = createUsuarioValid();
		
		// Insiro o objeto no banco H2
		testEntityManager.persist(usuarioEntity);
		
		//System.out.println(usuarioEntity);
		
		Optional<UsuarioEntity> usuario= usuarioRepository
					.findById( usuarioEntity.getId() ); 
		
		assertThat( usuario ).isPresent();
	}
	
	@Test
	void findByIdWhenNotFoundUser() {
		
		Optional<UsuarioEntity> usuario = usuarioRepository
				.findById(1); 
		
		assertThat( usuario ).isEmpty();
		
	}
	
	@Test()
	void saveTest() {
		
		UsuarioEntity usuarioEntity = createUsuarioValid();
		
		// executar teste
		UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);
		
		assertThat( usuarioSalvo.getId()).isPositive();
		assertThat( usuarioSalvo.getEmail() ).isEqualTo( usuarioEntity.getEmail() );
		assertThat( usuarioSalvo.getNome() ).isEqualTo( usuarioEntity.getNome() );
	
	}
	
	@Test
	void deleteByIdTest() {
		
		UsuarioEntity usuarioEntity = createUsuarioValid();
		
		// inserir registro no banco
		// Insiro o objeto no banco H2
		testEntityManager.persist(usuarioEntity);
		
		// execução do teste
		usuarioRepository.deleteById(usuarioEntity.getId());
		
		// testando - procurando o registro deletado
		
		Optional<UsuarioEntity> deletado = usuarioRepository
					.findById(usuarioEntity.getId());
		
		assertThat( deletado ).isEmpty();
		
	}
	
	@Test
	void findByNomeContainsTest(){
		
		UsuarioEntity usuarioEntity = createUsuarioValid();
		
		// inserir registro no banco
		// Insiro o objeto no banco H2
		testEntityManager.persist(usuarioEntity);
		
		// execução do teste
		List<UsuarioEntity> lista = usuarioRepository
					.findByNomeContains( usuarioEntity.getNome() );
		
		assertThat( lista.size() ).isEqualTo(1);
		assertThat ( lista.get(0).getNome() ).isEqualTo(usuarioEntity.getNome());
	}
	
}