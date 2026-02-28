package br.com.cezardev.dto;

import org.springframework.beans.BeanUtils;

import br.com.cezardev.entity.PacienteEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PacienteDTO {
	
	private Long id;
	private String descricao;
	
	public PacienteDTO(PacienteEntity paciente) {
		BeanUtils.copyProperties(paciente, this);
	}

}
