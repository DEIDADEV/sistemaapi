package br.com.cezardev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cezardev.entity.MedicoEntity;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long>{

}
