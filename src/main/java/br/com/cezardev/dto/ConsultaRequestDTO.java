package br.com.cezardev.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.BeanUtils;

import br.com.cezardev.entity.ConsultaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("deprecation")
@Getter
@Setter
@NoArgsConstructor
public class ConsultaRequestDTO {
	
	@NotNull
	private Long medigoID;
	
	@NotNull
	private Long paciente;
	
	@NotNull
	private LocalDate data;
	
	@NotNull
	private LocalTime horario;
	
	public ConsultaRequestDTO(ConsultaEntity consulta) {
		BeanUtils.copyProperties(consulta, this);
	}

	public Long getMedicoID() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getPacienteId() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getMedicoId() {
		// TODO Auto-generated method stub
		return null;
	}

}
