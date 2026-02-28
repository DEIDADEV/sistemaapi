package br.com.cezardev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cezardev.entity.AgendaEntity;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {

}
