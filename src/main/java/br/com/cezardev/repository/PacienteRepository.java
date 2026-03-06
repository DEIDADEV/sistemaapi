package br.com.cezardev.repository;

import br.com.cezardev.entity.PacienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    Optional<PacienteEntity> findByCpf(String cpf);

    // 🔎 Busca por nome ou CPF
    Page<PacienteEntity> findByNomeContainingIgnoreCase(
            String nome,
            Pageable pageable
    );

    Page<PacienteEntity> findByCpfContaining(
            String cpf,
            Pageable pageable
    );

	Page<PacienteEntity> findByNomeContainingIgnoreCaseOrCpfContaining(String filtroLimpo, String filtroLimpo2,
			PageRequest pageable);
}