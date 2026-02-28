package br.com.cezardev.dto;

import org.springframework.beans.BeanUtils;

import br.com.cezardev.entity.MedicoEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicoDTO {
	
	private Long id;
	private String descricao;
	
	public MedicoDTO(MedicoEntity medico) {
		BeanUtils.copyProperties(medico, this);
	}

}
