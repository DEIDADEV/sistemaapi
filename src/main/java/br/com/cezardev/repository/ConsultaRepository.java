package br.com.cezardev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.cezardev.entity.ConsultaEntity;
import br.com.cezardev.entity.MedicoEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {

    List<ConsultaEntity> findByMedicoId(Long medicoId);
    List<ConsultaEntity> findByPacienteId(Long pacienteId);

    // Alterado para aceitar MedicoEntity diretamente
    boolean existsByMedicoAndDataAndHorario(MedicoEntity medico, LocalDate data, LocalTime horario);
}