package com.example.cep.dto;

import lombok.Data;

@Data
public class EnderecoDTO {
	
	private String endereco;
	private String numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String uf;
	private String cep;

}
