package br.com.digisystem.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.digisystem.dtos.ProfessorDTO;
import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.repositories.ProfessorRepository;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ProfessorControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@Test
	void getAllTest() throws Exception {
		
		ResultActions response = mockMvc.perform(
				get("/professores")
				.contentType("application/json")
		);
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		
		ProfessorDTO[] lista = mapper.readValue(resultStr, ProfessorDTO[].class);
		
		assertThat(lista).isNotEmpty();
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
	}
	
	@Test
	void getOneTest() throws Exception {
		
		int id = 1;
		
		ResultActions response = mockMvc.perform(
				get("/professores/" + id)
				.contentType("application/json")
		);
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		ProfessorDTO professor = mapper.readValue(resultStr, ProfessorDTO.class);
		
		assertThat( professor.getId() ).isEqualTo(id);
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
		
	}
	@Test
	void createTest() throws Exception {
		
		// criar um usuarioDTO para enviar no corp da requisição
		
		ProfessorDTO professor = new ProfessorDTO();
		
		professor.setNome("Fabrizio JUNIT");
		professor.setEmail("junit@fabrizio.com");
		
		ResultActions response = mockMvc.perform(
				post("/professores")
				.contentType("application/json")
				.content( mapper.writeValueAsString(professor) )
		);
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		ProfessorDTO professorSalvo = mapper.readValue(resultStr, ProfessorDTO.class);
		
		assertThat( professorSalvo.getId() ).isPositive();
		assertThat( professorSalvo.getNome() ).isEqualTo( professor.getNome() );
		assertThat( professorSalvo.getEmail() ).isEqualTo( professor.getEmail() );
		assertThat( professorSalvo.getTelefone() ).isEqualTo( professor.getTelefone() );
		assertThat( professorSalvo.getCpf() ).isEqualTo( professor.getCpf() );
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
		
	}
	
	@Test
	void updateTest() throws Exception {
		
		int id = 3;
		
		ProfessorDTO professor = new ProfessorDTO();
		
		professor.setNome("Fabrizio JUNIT");
		professor.setEmail("junit@fabrizio.com");
		professor.setCpf("1234567822");
		professor.setTelefone("123456789");
		
		ResultActions response = mockMvc.perform(
				patch("/professores/" + id)
				.contentType("application/json")
				.content( mapper.writeValueAsString(professor) )
		);
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		ProfessorDTO professorAlterado = mapper.readValue(resultStr, ProfessorDTO.class);
		
		assertThat( professorAlterado.getId() ).isEqualTo(id);
		assertThat( professorAlterado.getNome() ).isEqualTo( professor.getNome() );
		assertThat( professorAlterado.getEmail() ).isEqualTo( professor.getEmail() );
		assertThat( professorAlterado.getTelefone() ).isEqualTo( professor.getTelefone() );
		assertThat( professorAlterado.getCpf() ).isEqualTo( professor.getCpf() );
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
		
	}
	@Test
	void deleteTest() throws Exception {
		 //inserindo registro no banco de dados
		ProfessorEntity professor = new ProfessorEntity();
		
		professor.setNome("Fabrizio JUNIT");
		professor.setEmail("junit@fabrizio.com");
		professor.setCpf("1234567822");
		professor.setTelefone("123456789");
		
		ProfessorEntity professorSalvo = professorRepository.save(professor);
		
		 //deleto o registro inserido anteriormente
		ResultActions response = mockMvc.perform(
				delete("/professores/" + professorSalvo.getId())
				.contentType("application/json")
		);
		
		MvcResult result = response.andReturn();
		
		assertThat( result.getResponse().getStatus() )
			.isEqualTo( HttpStatus.OK.value() );
	}
	
	@Test
	void getByNomeTest() throws Exception {
		
		String nome = "Fabrizio";
		
		ResultActions response = mockMvc.perform(
				get("/professores/get-by-nome/" + nome)
				.contentType("application/json")
		);
		
		MvcResult result = response.andReturn();
		
		String resultStr = result.getResponse().getContentAsString();
		
		ProfessorDTO[] professoresLista = mapper.readValue(resultStr, ProfessorDTO[].class);
		
		assertThat( professoresLista ).isNotEmpty();
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
		
	}
	@Test
	void updateProfessor() throws Exception {
		
		int id = 3;
		String nome = "Fabrizio";
		
		ProfessorDTO professor = new ProfessorDTO();
		professor.setNome(nome);
		
		ResultActions response = mockMvc.perform(
				patch("/professores/update/" + id)
				.contentType("application/json")
				.content( mapper.writeValueAsString(professor) )
		);
		
		MvcResult result = response.andReturn();
		
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
	}
	
	
}