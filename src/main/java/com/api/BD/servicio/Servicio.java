package com.api.BD.servicio;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.BD.model.Modelo;
import com.api.BD.repository.IUrep;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class Servicio {
	@Autowired
	private IUrep usuarioRepository;

	public boolean autenticar(String email, String contra) {
		Modelo usuario = usuarioRepository.findByEmailAndContra(email, contra);
		return usuario != null;
	}


}
	

