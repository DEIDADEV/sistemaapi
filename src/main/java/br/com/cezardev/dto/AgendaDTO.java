package br.com.cezardev.dto;

import org.springframework.beans.BeanUtils;

import br.com.cezardev.entity.AgendaEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AgendaDTO {
	
	private Long id;
	private String descricao;
	
	public AgendaDTO(AgendaEntity agenda) {
		BeanUtils.copyProperties(agenda, this);
	}

}
