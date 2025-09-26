package br.com.ceub.gastroreserva.mapper;

import br.com.ceub.gastroreserva.dto.ReservaDTO;
import br.com.ceub.gastroreserva.entities.Mesa;
import br.com.ceub.gastroreserva.entities.Reserva;
import br.com.ceub.gastroreserva.entities.Restaurante;
import br.com.ceub.gastroreserva.entities.Usuario;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;


public class ReservaMapper {

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
        AtomicReference<Long> codMesa = new AtomicReference<>();
        var mesaOptional = reserva.getRestaurante()
                .getMesa()
                .stream()
                .findFirst();

        mesaOptional.ifPresentOrElse(
                mesa -> codMesa.set(mesa.getId()),
                () -> codMesa.set(null)
        );

        return ReservaDTO
                .builder()
                .dataAgendamento(reserva.getDataAgendamento())
                .codMesa(codMesa.get())
                .codRestaurante(reserva.getRestaurante().getId())
                .codUsuario(reserva.getUsuario().getId())
                .comentario(reserva.getComentario())
                .nomeCliente(reserva.getUsuario().getNome())
                .checkedIn(reserva.isCheckedIn())
                .build();
    }

}
