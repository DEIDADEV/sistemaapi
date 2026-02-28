package br.com.cezardev.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.cezardev.entity.ConsultaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConsultaDTO {

    private Long id;
    private Long pacienteId;
    private Long agendaId;
    private String status; // caso você tenha o status na entity
    private LocalDate data;   // se estiver usando String para data
    private LocalTime horario; // se estiver usando String ou Time para horário

    // Construtor que recebe a entity
    public ConsultaDTO(ConsultaEntity entity) {
        this.id = entity.getId();
        this.pacienteId = entity.getPacienteId();
        this.agendaId = entity.getAgendaId();
        this.status = entity.getStatus();
        this.data = entity.getData();
        this.horario = entity.getHorario();
    }

    // Método para converter DTO em entity
    public ConsultaEntity toEntity() {
        ConsultaEntity entity = new ConsultaEntity();
        entity.setId(this.id);
        entity.setPacienteId(this.pacienteId);
        entity.setAgendaId(this.agendaId);
        entity.setStatus(this.status);
        entity.setData(this.data);
        entity.setHorario(this.horario);
        return entity;
    }

	public Long getMedicoId() {
		// TODO Auto-generated method stub
		return null;
	}
}