package br.com.cezardev.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AGENDA")
@Getter
@Setter
@NoArgsConstructor
public class AgendaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String medicoID;
	
	@Column(nullable = false)
	private String diasSemana;
	
	@Column(nullable = false)
	private String horarios;

}
