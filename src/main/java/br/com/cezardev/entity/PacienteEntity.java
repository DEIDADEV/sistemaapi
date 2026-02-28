package br.com.cezardev.entity;

import org.springframework.beans.BeanUtils;

import br.com.cezardev.dto.PacienteDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "PACIENTE")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PacienteEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String cpf;
    
    @Column(nullable = false)
    private String dataNascimento;
    
    public PacienteEntity(PacienteDTO paciente) {
        BeanUtils.copyProperties(paciente, this);
    }
}