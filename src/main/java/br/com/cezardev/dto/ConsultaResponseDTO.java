package br.com.cezardev.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultaResponseDTO {
    private Long id;
    private String medicoNome;
    private String pacienteNome;
    private String data;
    private String horario;
    private String status;
}