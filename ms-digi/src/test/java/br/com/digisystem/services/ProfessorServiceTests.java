package br.com.digisystem.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.exceptions.ObjNotFoundException;
import br.com.digisystem.repositories.ProfessorRepository;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ProfessorServiceTests {
	
	@Autowired
	private ProfessorService professorService;
	
	@MockBean
	private ProfessorRepository professorRepository;
	
	@Test
	void getAllTest() {
		
		List<ProfessorEntity> professorSimulado = new ArrayList<>();
		ProfessorEntity professor = new ProfessorEntity();
		professor.setEmail("Email@email.com");
		professor.setNome("Nome Teste");
		professor.setCpf("CPF Teste");
		professor.setTelefone("TELEFONE Teste");
		
		professorSimulado.add(professor);
		
		when ( professorRepository.findAll() ).thenReturn( professorSimulado );
		
		List<ProfessorEntity> lista = professorService.getAll();
		
		assertThat( professorSimulado.get(0).getNome() )
			.isEqualTo(lista.get(0).getNome());
		
		assertThat( professorSimulado.get(0).getEmail() )
			.isEqualTo(lista.get(0).getEmail());
		
		assertThat( professorSimulado.get(0).getCpf() )
		.isEqualTo(lista.get(0).getCpf());
		
		assertThat( professorSimulado.get(0).getTelefone() )
		.isEqualTo(lista.get(0).getTelefone());
	}
	
	@Test
	void getOneWhenFoundUserTest() {

		int id = 1;
		
		ProfessorEntity professorEntity = new ProfessorEntity();
		professorEntity.setEmail("Email@email.com");
		professorEntity.setNome("Nome Teste");
		professorEntity.setCpf("CPF Teste");
		professorEntity.setTelefone("TELEFONE Teste");
		professorEntity.setId(id);
		
		Optional<ProfessorEntity> optional = Optional.of(professorEntity);

		when ( professorRepository.findById(id) ).thenReturn(optional);

		ProfessorEntity professor = professorService.getOne(id);
		
		assertThat( professor.getNome() )
			.isEqualTo(professorEntity.getNome());
	
		assertThat( professor.getEmail() )
			.isEqualTo( professorEntity.getEmail());
		
		assertThat( professor.getCpf() )
		.isEqualTo( professorEntity.getCpf());
		
		assertThat( professor.getTelefone() )
		.isEqualTo( professorEntity.getTelefone());
		
	}
	
	@Test
	void getOneWhenNotFoundUserTest() {
		
		int id = 1;

		when ( professorRepository.findById(id) )
			.thenThrow(  new ObjNotFoundException("Erro") );

	 	assertThrows(ObjNotFoundException.class, ()-> professorService.getOne(id) );
	}
	
	@Test
	void createTest() {
		
		
		int id = 1;
		
		ProfessorEntity professorEntity = new ProfessorEntity();
		professorEntity.setEmail("Email@email.com");
		professorEntity.setNome("Nome Teste");
		professorEntity.setCpf("CPF Teste");
		professorEntity.setTelefone("TELEFONE Teste");
		
		ProfessorEntity professorEntityRetorno = new ProfessorEntity();
		professorEntityRetorno.setEmail("Email@email.com");
		professorEntityRetorno.setNome("Nome Teste");
		professorEntityRetorno.setCpf("CPF Teste");
		professorEntityRetorno.setTelefone("TELEFONE Teste");
		professorEntityRetorno.setId(id);
		
		when( professorRepository.save( professorEntity ) ).thenReturn( professorEntityRetorno );
		

		ProfessorEntity professorSalvo = professorService.save(professorEntity);
		
		assertThat( professorSalvo.getNome() )
			.isEqualTo(professorEntity.getNome());
	
		assertThat( professorSalvo.getEmail() )
			.isEqualTo( professorEntity.getEmail());
		
		assertThat( professorSalvo.getCpf() )
		.isEqualTo( professorEntity.getCpf());
		
		assertThat( professorSalvo.getTelefone() )
		.isEqualTo( professorEntity.getTelefone());
		
		assertThat( professorSalvo.getId() ).isPositive();
		
	}
	
	@Test
	void updateWhenFoundUser() {
		
		//int id = 1;
		
		ProfessorEntity professorValid = this.createValidProfessor(true);
		
		when( professorRepository.findById(1) ).thenReturn( Optional.of(professorValid) );
		when (professorRepository.save(professorValid)).thenReturn(professorValid);
		
		// executar o teste
		
		ProfessorEntity professorAlterado = professorService.update(1, professorValid);
		
		// verificar se o teste deu certo
		assertThat( professorAlterado.getNome() )
			.isEqualTo(professorValid.getNome());

		assertThat( professorAlterado.getEmail() )
			.isEqualTo( professorValid.getEmail());
		
		assertThat( professorAlterado.getTelefone() )
		.isEqualTo( professorValid.getTelefone());
		
		assertThat( professorAlterado.getCpf() )
		.isEqualTo( professorValid.getCpf());
	}
	
	@Test
	void updateWhenNotFoundUser() {
		
		when( professorRepository.findById(1) ).thenReturn( Optional.empty() );
		
		// executar o teste
		
		ProfessorEntity professorAlterado = professorService.update(1, null);
		
		assertThat( professorAlterado ).isNull();
		
	}
	
	@Test
	void deleteTest() {
		
		//execução
		
		assertDoesNotThrow( () -> professorService.delete(1) );
		
		// verifico se o método usuarioRepository.deleteById() foi executado apenas uma vez
		verify(professorRepository, times(1) ).deleteById(1);
	}
	
	@Test
	void getByNomeTest() {
		
		// criar a lista simulada
		ProfessorEntity professorValid = this.createValidProfessor(true);
		
//		List<UsuarioEntity> lista = new ArrayList<>();
//		lista.add(usuarioValid);
//		lista.add(usuarioValid);
		List<ProfessorEntity> lista = Arrays.asList(professorValid, professorValid);
		
		when( professorRepository.searchByNomeNativo("nome")).thenReturn(lista);
		

		// verificar teste
		
		//lista não é vazia
		assertThat( lista ).isNotEmpty();
		
		//não retornou uma exceção quando chamou o método de teste
		assertDoesNotThrow( () -> professorRepository.searchByNomeNativo("nome") );
		
	}
	
	private ProfessorEntity createValidProfessor(boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	private ProfessorEntity createValidUsuario( boolean isId ) {
	
		int id = 1;
		
		ProfessorEntity professorEntity = new ProfessorEntity();
		
		professorEntity.setEmail("Email@email.com");
		professorEntity.setNome("Nome Teste");
		professorEntity.setCpf("123455678");
		professorEntity.setTelefone("234456789");
		
		if (isId == true) {
			professorEntity.setId(id);
		}
		
		return professorEntity;
	}

}

