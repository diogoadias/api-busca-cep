package com.example.cep.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.cep.dto.EnderecoDTO;
import com.example.cep.entity.Endereco;
import com.example.cep.repository.CepRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CepService {
	
	@Value("${url.mock.cep}")
	private String url;
	
	@Autowired
	CepRepository cepRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Retryable(maxAttemptsExpression = "${retry.maxAttemps}", backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
	public ResponseEntity<EnderecoDTO> buscarCep(String valor) {
		
		try {
			String urlFull = url + valor;
			
			ResponseEntity<EnderecoDTO> enderecoDTO = restTemplate.getForEntity(urlFull, EnderecoDTO.class);			
			
			if(enderecoDTO.getBody() != null) {
				Endereco endereco = Endereco.builder().bairro(enderecoDTO.getBody().getBairro())
						  .cep(enderecoDTO.getBody().getCep())
						  .cidade(enderecoDTO.getBody().getCidade())
						  .complemento(enderecoDTO.getBody().getComplemento())
						  .endereco(enderecoDTO.getBody().getEndereco())
						  .numero(enderecoDTO.getBody().getNumero())
						  .uf(enderecoDTO.getBody().getUf())
						  .dataConsulta(LocalDateTime.now())
						  .build();	
				
				cepRepository.save(endereco);
			}
			
			return enderecoDTO;
			
		} catch (HttpClientErrorException e) {			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}		
		
	}

}
