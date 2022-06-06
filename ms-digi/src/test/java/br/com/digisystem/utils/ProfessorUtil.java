package br.com.digisystem.utils;

import br.com.digisystem.entities.ProfessorEntity;

public class ProfessorUtil {

	protected ProfessorEntity createProfessorValid() {
		
		ProfessorEntity professorEntity = new ProfessorEntity();
		
		professorEntity.setTelefone("telefone");
		professorEntity.setCpf("cpf");
		professorEntity.setEmail("email");
		professorEntity.setNome("nome");
		
		return professorEntity;
		
	}

}