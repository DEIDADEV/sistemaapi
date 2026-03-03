package br.com.cezardev.repository;

import br.com.cezardev.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    // 🔎 Buscar paciente pelo CPF
    Optional<PacienteEntity> findByCpf(String cpf);

}