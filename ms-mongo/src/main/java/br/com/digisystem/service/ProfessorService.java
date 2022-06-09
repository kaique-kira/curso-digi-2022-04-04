package br.com.digisystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digisystem.entities.ProfessorEntity;
import br.com.digisystem.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository professorRepository;
	
	public List<ProfessorEntity> getAll(){
		return this.professorRepository.findAll();
	}
	
	public ProfessorEntity getOne(int id) {
		return this.professorRepository.findById(id).orElseThrow();
	}
//	
//	public ProfessorEntity save(ProfessorEntity professor) {
//		return this.professorRepository.save(professor);
//	}
//	
//	public ProfessorEntity update(int id, ProfessorEntity professor) {
//		
//		ProfessorEntity professorUpdate = this.professorRepository.
//				findById(id).orElseThrow();
//		
//		professorUpdate.setCpf( professor.getCpf() );
//		professorUpdate.setEmail( professor.getEmail() );
//		professorUpdate.setNome( professor.getNome() );
//		professorUpdate.setTelefone(professor.getTelefone() );
//		
//		return this.professorRepository.save(professorUpdate);
//	}
//	
//	public void delete(int id) {
//		try {
//			this.professorRepository.deleteById(id);
//		}
//		catch (Exception e) {
//			System.out.println("Erro ao deletar o registro");
//		}
//		
//	}
	
	public List<ProfessorEntity> getByNome(String nome){
		//return this.usuarioRepository.findByNomeContains(nome);
		return this.professorRepository.searchByNomeNativo(nome);
	}
	
	@Transactional
	public void updateProfessor(int id, String nome) {
		this.professorRepository.updateProfessor(id, nome);
	}
}