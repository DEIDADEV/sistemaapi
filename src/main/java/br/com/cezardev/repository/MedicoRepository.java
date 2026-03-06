package br.com.cezardev.repository;

import br.com.cezardev.entity.MedicoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {

    Optional<MedicoEntity> findByCrm(String crm);

    Page<MedicoEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

    Page<MedicoEntity> findByCrmContaining(String crm, Pageable pageable);
}