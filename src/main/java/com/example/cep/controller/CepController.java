package com.example.cep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.cep.api.CepApi;
import com.example.cep.dto.EnderecoDTO;
import com.example.cep.service.CepService;

@RestController
public class CepController implements CepApi {
	
	@Autowired
	private CepService cepService;
	
	@Override
	@GetMapping(value = "/cep/{valor}")
	public ResponseEntity<EnderecoDTO> buscarCep(@PathVariable String valor) {
		
		return cepService.buscarCep(valor);
	}

}
