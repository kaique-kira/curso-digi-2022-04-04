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

import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.utils.ProfessorUtil;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ProfessorRepositoryTests extends ProfessorUtil {

	@Autowired
	private ProfessorRepository professorRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Test
	void findAllTests() {
		// crio o objeto a ser inserido no H2
//		UsuarioEntity usuarioEntity = new UsuarioEntity();
//		usuarioEntity.setEmail("email");
//		usuarioEntity.setNome("nome");
		
		ProfessorEntity professorEntity = createProfessorValid();
		
		// Insiro o objeto no banco H2
		testEntityManager.persist(professorEntity);
		
		//realizar o teste
		
		List<ProfessorEntity> lista = professorRepository.findAll();
		
		assertThat( lista.size() ).isEqualTo(1);
	}
	
	@Test
	void findByIdWhenFoundUser() {
		
		ProfessorEntity professorEntity = createProfessorValid();
		
		// Insiro o objeto no banco H2
		testEntityManager.persist(professorEntity);
		
		//System.out.println(usuarioEntity);
		
		Optional<ProfessorEntity> professor= professorRepository
					.findById( professorEntity.getId() ); 
		
		assertThat( professor ).isPresent();
	}
	
	@Test
	void findByIdWhenNotFoundUser() {
		
		Optional<ProfessorEntity> professor = professorRepository
				.findById(1); 
		
		assertThat( professor ).isEmpty();
		
	}
	
	@Test()
	void saveTest() {
		
		ProfessorEntity professorEntity = createProfessorValid();
		
		// executar teste
		ProfessorEntity professorSalvo = professorRepository.save(professorEntity);
		
		assertThat( professorSalvo.getId()).isPositive();
		assertThat( professorSalvo.getEmail() ).isEqualTo( professorEntity.getEmail() );
		assertThat( professorSalvo.getNome() ).isEqualTo( professorEntity.getNome() );
		assertThat( professorSalvo.getCpf() ).isEqualTo( professorEntity.getCpf() );
		assertThat( professorSalvo.getTelefone() ).isEqualTo( professorEntity.getTelefone() );
	
	}
	
	@Test
	void deleteByIdTest() {
		
		ProfessorEntity professorEntity = createProfessorValid();
		
		// inserir registro no banco
		// Insiro o objeto no banco H2
		testEntityManager.persist(professorEntity);
		
		// execução do teste
		professorRepository.deleteById(professorEntity.getId());
		
		// testando - procurando o registro deletado
		
		Optional<ProfessorEntity> deletado = professorRepository
					.findById(professorEntity.getId());
		
		assertThat( deletado ).isEmpty();
		
	}
	
	@Test
	void findByNomeContainsTest(){
		
		ProfessorEntity professorEntity = createProfessorValid();
		
		// inserir registro no banco
		// Insiro o objeto no banco H2
		testEntityManager.persist(professorEntity);
		
		// execução do teste
		List<ProfessorEntity> lista = professorRepository
					.findByNomeContains( professorEntity.getNome() );
		
		assertThat( lista.size() ).isEqualTo(1);
		assertThat ( lista.get(0).getNome() ).isEqualTo(professorEntity.getNome());
	}

}