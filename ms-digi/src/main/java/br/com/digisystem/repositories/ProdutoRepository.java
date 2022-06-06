package br.com.digisystem.repositories;

import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.ProdutoEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Integer>{

}