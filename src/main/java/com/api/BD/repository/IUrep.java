package com.api.BD.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.BD.model.Modelo;

public interface IUrep extends JpaRepository<Modelo, Long> {

	Modelo findByEmailAndContra(String email, String contra);

	
	
	
}
