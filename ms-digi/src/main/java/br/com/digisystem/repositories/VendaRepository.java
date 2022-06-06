package br.com.digisystem.repositories;

import org.springframework.stereotype.Repository;

import br.com.digisystem.entities.VendaEntity;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VendaRepository extends JpaRepository<VendaEntity,Integer>{

}