package br.com.cezardev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cezardev.entity.PacienteEntity;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long>{

}
