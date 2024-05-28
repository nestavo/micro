package com.api.BD.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.api.BD.model.Modelo;
import com.api.BD.repository.IUrep;
import com.api.BD.servicio.Servicio;
import com.api.BD.servicio.SessionService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpSession;

@SessionAttributes("userSession")
@RestController
@RequestMapping("/usuarios")
public class Controlador {

	@Autowired
	private SessionService sessionService;

	@Autowired
	private Servicio servicio;

	@Autowired
	private IUrep usuarioRepository;

	@CrossOrigin(origins = "http://127.0.0.1:3000")
	@GetMapping("/lista")
	public List<Modelo> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@CrossOrigin(origins = "http://127.0.0.1:3000")
	@PostMapping("/crearUsuario")
	public Modelo crearUsuario(@RequestBody Modelo modelo) {
		return usuarioRepository.save(modelo);
	}

	@CrossOrigin(origins = "http://127.0.0.1:3000")
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestParam String email, @RequestParam String contra, HttpSession session) {
		boolean autenticado = servicio.autenticar(email, contra);
		if (autenticado) {
			System.out.println("Autenticación exitosa");

			// Generar token de autenticación
		//	String token = generateAuthToken(email);

			session.setAttribute("userSession", email); // Guardar el email en la sesión

			// Crear o actualizar sesión en el servicio de gestión de sesiones
			sessionService.createSession(email, session);

			// Construir la URL de redirección con el token
			String redirectUrl = "http://127.0.0.1:3000/Proyecto2/cliente.html";

			HttpHeaders headers = new HttpHeaders();
			headers.add("Location", redirectUrl);

			// Redirigir con el token en la URL
			return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
		}
	}

	// Método para generar un token de autenticación
	public String generateAuthToken(String email) {
		// Clave secreta para firmar el token (deberías almacenarla de forma segura)
		String secretKey = "1234";

		// Tiempo de expiración del token (por ejemplo, 1 hora)
		long expirationTime = 3600000; // 1 hora en milisegundos

		// Fecha de expiración del token
		Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

		// Generar el token JWT
		String authToken = Jwts.builder().setSubject(email) // Establecer el correo electrónico como sujeto del token
				.setExpiration(expirationDate) // Establecer la fecha de expiración
				.signWith(SignatureAlgorithm.HS512, secretKey) // Firmar el token con el algoritmo HS512 y la clave
																// secreta
				.compact();

		return authToken;
	}

}
