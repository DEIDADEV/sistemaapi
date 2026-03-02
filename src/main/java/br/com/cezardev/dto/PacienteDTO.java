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
    private String nome;
    private String cpf;
    private String dataNascimento;

    public PacienteDTO(PacienteEntity entity) {
        BeanUtils.copyProperties(entity, this);
    }

    public PacienteEntity toEntity() {
        PacienteEntity entity = new PacienteEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}