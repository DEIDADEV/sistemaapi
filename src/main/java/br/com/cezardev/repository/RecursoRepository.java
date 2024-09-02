package br.com.cezardev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cezardev.entity.RecursoEntity;

public interface RecursoRepository extends JpaRepository<RecursoEntity, Long> {

}
