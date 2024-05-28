package com.api.BD.servicio;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {
    private Map<String, HttpSession> sessions;

    public SessionService() {
        this.sessions = new HashMap<>();
    }

    public void createSession(String email, HttpSession session) {
        sessions.put(email, session);
    }

    public void removeSession(String email) {
        sessions.remove(email);
    }

    public HttpSession getSession(String email) {
        return sessions.get(email);
    }

    // Otros métodos según necesidades de gestión de sesiones

    // Ejemplo de método para verificar si un email tiene sesión activa
    public boolean hasActiveSession(String email) {
        return sessions.containsKey(email);
    }
    
}
