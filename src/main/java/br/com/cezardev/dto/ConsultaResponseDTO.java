package br.com.cezardev.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ConsultaResponseDTO {
	
	public ConsultaResponseDTO(Long id2, String nome, String nome2, LocalDate data2, LocalTime horario2,
			String status2) {
		// TODO Auto-generated constructor stub
	}
	private Long id;
	private Long medicoId;
	private Long pacienteId;
	private String medicoNome;
	private String pacienteNome;
	private LocalDate data;
	private LocalTime horario;
	private String status;

}
