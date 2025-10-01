package br.com.ceub.gastroreserva.mapper;

import br.com.ceub.gastroreserva.dto.ReservaDTO;
import br.com.ceub.gastroreserva.entities.Mesa;
import br.com.ceub.gastroreserva.entities.Reserva;
import br.com.ceub.gastroreserva.entities.Restaurante;
import br.com.ceub.gastroreserva.entities.Usuario;

import java.util.Optional;



public class ReservaMapper {

    // Método toEntity (Não alterado)
    public static Reserva toEntity(ReservaDTO reservaDTO, Usuario usuario, Restaurante restaurante, Optional<Mesa> mensaSelecionada) {


        return Reserva
                .builder()
                .usuario(usuario)
                .restaurante(restaurante)
                .dataAgendamento(reservaDTO.getDataAgendamento())
                .comentario(reservaDTO.getComentario())
                .mesa(mensaSelecionada.orElse(null))
                .build();
    }

    public static ReservaDTO toDTO(Reserva reserva) {


        Long codMesa = reserva.getMesa() != null ? reserva.getMesa().getId() : null;

        return ReservaDTO
                .builder()
                .id(reserva.getId()) // Retorna ID da reserva (essencial para o Check-in)
                .dataAgendamento(reserva.getDataAgendamento())
                .codMesa(codMesa) // Usa a mesa associada à reserva
                .codRestaurante(reserva.getRestaurante().getId())
                .codUsuario(reserva.getUsuario().getId())
                .comentario(reserva.getComentario())
                .nomeCliente(reserva.getUsuario().getNome())
                .checkedIn(reserva.isCheckedIn())
                .checkedOut(reserva.isCheckedOut())
                .build();
    }
}
