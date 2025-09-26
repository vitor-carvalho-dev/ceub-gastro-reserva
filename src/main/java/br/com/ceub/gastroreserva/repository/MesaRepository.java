package br.com.ceub.gastroreserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.ceub.gastroreserva.entities.Mesa;

import java.util.List;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
    List<Mesa> findByRestauranteId(Long restauranteId);
}

