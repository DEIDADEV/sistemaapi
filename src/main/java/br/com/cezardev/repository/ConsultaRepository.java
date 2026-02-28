package br.com.cezardev.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cezardev.entity.ConsultaEntity;
import br.com.cezardev.entity.MedicoEntity;

public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long>{
	
	boolean existsByMedicoAndDataAndHorario(
            MedicoEntity medico,
            LocalDate data,
            LocalTime horario
    );

    List<ConsultaEntity> findByMedicoAndData(
            MedicoEntity medico,
            LocalDate data
    );

}
