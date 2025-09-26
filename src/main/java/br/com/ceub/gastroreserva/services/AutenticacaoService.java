package br.com.ceub.gastroreserva.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AutenticacaoService {

    public boolean verificarSeUsuarioEstaAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication != null && authentication.isAuthenticated() && !authentication.getName().equals("anonymousUser");
    }

}
