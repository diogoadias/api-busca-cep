package com.example.cep.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Endereco {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String endereco;
	
	@Column
	private String numero;
	
	@Column
	private String complemento;
	
	@Column
	private String bairro;
	
	@Column
	private String cidade;
	
	@Column
	private String uf;
	
	@Column
	private String cep;
	
	@Column	
	private LocalDateTime dataConsulta;


}
