package br.com.digisystem.repositories;

import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.EnderecoEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity,Integer>{

}