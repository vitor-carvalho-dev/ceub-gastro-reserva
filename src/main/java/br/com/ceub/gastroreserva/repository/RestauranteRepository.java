package br.com.ceub.gastroreserva.repository;

import br.com.ceub.gastroreserva.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    boolean existsByNome(String nome);
}
