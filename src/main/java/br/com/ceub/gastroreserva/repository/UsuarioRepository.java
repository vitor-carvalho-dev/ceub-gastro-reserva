package br.com.ceub.gastroreserva.repository;

import br.com.ceub.gastroreserva.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
