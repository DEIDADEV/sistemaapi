package br.com.cezardev.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.BeanUtils;

import br.com.cezardev.dto.ConsultaDTO;
import br.com.cezardev.dto.ConsultaRequestDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
	    name = "CONSULTA",
	    uniqueConstraints = {
	        @UniqueConstraint(
	            columnNames = {"medico_id", "data", "horario"}
	        )
	    }
	)
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class ConsultaEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private MedicoEntity medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteEntity paciente;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horario;

    private String status;
	
	public ConsultaEntity(ConsultaRequestDTO consulta) {
		BeanUtils.copyProperties(consulta, this);
	}

	public Long getPacienteId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getAgendaId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPacienteId(Long pacienteId) {
		// TODO Auto-generated method stub
		
	}

	public void setAgendaId(Long agendaId) {
		// TODO Auto-generated method stub
		
	}

	public void setData(LocalDate data2) {
		// TODO Auto-generated method stub
		
	}

	public void setHorario(LocalTime horario2) {
		// TODO Auto-generated method stub
		
	}

	public ConsultaEntity(ConsultaRequestDTO dto, MedicoEntity medico2, PacienteEntity paciente2) {
		// TODO Auto-generated constructor stub
	}

	public ConsultaEntity(ConsultaDTO dto, MedicoEntity medico2, PacienteEntity paciente2) {
		// TODO Auto-generated constructor stub
	}

}
