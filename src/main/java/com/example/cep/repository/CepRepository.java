package com.example.cep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cep.entity.Endereco;

@Repository
public interface CepRepository extends JpaRepository<Endereco, Long> {

}
