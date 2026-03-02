package br.com.cezardev.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class ConsultaDTO {
    private Long id;
    private Long medicoId;
    private Long pacienteId;
    private LocalDate data;
    private LocalTime horario;
    private String status;
    
    public ConsultaDTO(Long id, String status) {
        this.id = id;
        this.status = status;
    }
    
}