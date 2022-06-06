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

import br.com.digisystem.dtos.UsuarioDTO;
import br.com.digisystem.entities.UsuarioEntity;
import br.com.digisystem.repositories.UsuarioRepository;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UsuarioControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Test
	void getAllTest() throws Exception {
		
		ResultActions response = mockMvc.perform(
				get("/usuarios")
				.contentType("application/json")
		);
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		//System.out.println(resultStr);
		
		UsuarioDTO[] lista = mapper.readValue(resultStr, UsuarioDTO[].class);
		
		assertThat(lista).isNotEmpty();
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
	}
	
	@Test
	void getOneTest() throws Exception {
		
		int id = 3;
		
		ResultActions response = mockMvc.perform(
				get("/usuarios/" + id)
				.contentType("application/json")
		);
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		UsuarioDTO usuario = mapper.readValue(resultStr, UsuarioDTO.class);
		
		assertThat( usuario.getId() ).isEqualTo(id);
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
		
	}
	
	@Test
	void createTest() throws Exception {
		
		// criar um usuarioDTO para enviar no corp da requisição
		
		UsuarioDTO usuario = new UsuarioDTO();
		
		usuario.setNome("Fabrizio JUNIT");
		usuario.setEmail("junit@fabrizio.com");
		
		ResultActions response = mockMvc.perform(
				post("/usuarios")
				.contentType("application/json")
				.content( mapper.writeValueAsString(usuario) )
		);
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		UsuarioDTO usuarioSalvo = mapper.readValue(resultStr, UsuarioDTO.class);
		
		assertThat( usuarioSalvo.getId() ).isPositive();
		assertThat( usuarioSalvo.getNome() ).isEqualTo( usuario.getNome() );
		assertThat( usuarioSalvo.getEmail() ).isEqualTo( usuario.getEmail() );
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
		
	}
	
	@Test
	void updateTest() throws Exception {
		
		int id = 3;
		
		UsuarioDTO usuario = new UsuarioDTO();
		
		usuario.setNome("Fabrizio JUNIT");
		usuario.setEmail("junit@fabrizio.com");
		
		ResultActions response = mockMvc.perform(
				patch("/usuarios/" + id)
				.contentType("application/json")
				.content( mapper.writeValueAsString(usuario) )
		);
		
		MvcResult result = response.andReturn();
		String resultStr = result.getResponse().getContentAsString();
		
		UsuarioDTO usuarioAlterado = mapper.readValue(resultStr, UsuarioDTO.class);
		
		assertThat( usuarioAlterado.getId() ).isEqualTo(id);
		assertThat( usuarioAlterado.getNome() ).isEqualTo( usuario.getNome() );
		assertThat( usuarioAlterado.getEmail() ).isEqualTo( usuario.getEmail() );
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
		
	}
	@Test
	void deleteTest() throws Exception {
		// inserindo um registro no banco de dados
		UsuarioEntity usuario = new UsuarioEntity();
		
		usuario.setNome("Fabrizio JUNIT");
		usuario.setEmail("junit@fabrizio.com");
		
		UsuarioEntity usuarioSalvo = usuarioRepository.save(usuario);
		
		// deleto o registro inserido anteriormente
		ResultActions response = mockMvc.perform(
				delete("/usuarios/" + usuarioSalvo.getId())
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
				get("/usuarios/get-by-nome/" + nome)
				.contentType("application/json")
		);
		
		MvcResult result = response.andReturn();
		
		String resultStr = result.getResponse().getContentAsString();
		
		UsuarioDTO[] usuarioLista = mapper.readValue(resultStr, UsuarioDTO[].class);
		
		assertThat( usuarioLista ).isNotEmpty();
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
		
	}
	@Test
	void updateUsuario() throws Exception {
		
		int id = 3;
		String nome = "Fabrizio";
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setNome(nome);
		
		ResultActions response = mockMvc.perform(
				patch("/usuarios/update/" + id)
				.contentType("application/json")
				.content( mapper.writeValueAsString(usuario) )
		);
		
		MvcResult result = response.andReturn();
		
		assertThat( result.getResponse().getStatus() ).isEqualTo( HttpStatus.OK.value() );
	}
	
}