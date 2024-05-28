package com.api.BD.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import jakarta.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuarios1")
public class Modelo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombreEmpresa;
	
	private String direccion;

	private String email;

	private String nombre;

	private long telefono;
	
	private String contra;

}
