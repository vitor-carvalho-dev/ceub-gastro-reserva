package br.com.ceub.gastroreserva.repository;

import br.com.ceub.gastroreserva.entities.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    @Query("select r from Reserva r where r.usuario.nome = ?1")
    Optional<Reserva> findByUsuario_Nome(String nome);

    @Query("""
            select (count(r) > 0) from Reserva r inner join r.restaurante.mesa mesa
            where r.usuario.cpf = ?1 and r.restaurante.id = ?2""")
    boolean existeReserva(String cpf, Long idRestaurante);


}
