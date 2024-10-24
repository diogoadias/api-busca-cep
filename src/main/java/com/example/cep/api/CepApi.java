package com.example.cep.api;

import org.springframework.http.ResponseEntity;

import com.example.cep.dto.EnderecoDTO;

public interface CepApi {
	
	public ResponseEntity<EnderecoDTO> buscarCep(String valor);

}
