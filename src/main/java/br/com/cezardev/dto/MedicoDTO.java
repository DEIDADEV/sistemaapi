package br.com.cezardev.dto;

import br.com.cezardev.entity.MedicoEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class MedicoDTO {

    private Long id;
    private String nome;
    private String crm;
    private String especialidade;

    // Construtor vazio
    public MedicoDTO() {
    }

    // Construtor que recebe Entity
    public MedicoDTO(MedicoEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }

    // Método para converter DTO em Entity
    public MedicoEntity toEntity() {
        MedicoEntity entity = new MedicoEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}