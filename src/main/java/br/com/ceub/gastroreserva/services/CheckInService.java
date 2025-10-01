package br.com.ceub.gastroreserva.services;

import br.com.ceub.gastroreserva.dto.CheckInDTO;
import br.com.ceub.gastroreserva.dto.ReservaDTO;
import br.com.ceub.gastroreserva.entities.Reserva;
import br.com.ceub.gastroreserva.exceptions.RecursoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CheckInService {
    private final ReservaService reservaService;

    public String realizarCheckin(CheckInDTO checkInDTO) {

        //  Busca pelo ID da reserva.
        Reserva reserva = reservaService.buscarReservaPorId(checkInDTO.getReservaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Reserva com ID " + checkInDTO.getReservaId() + " não encontrada.")); // Lança exceção se não encontrar

        // Validação se o nome do cliente confere.
        if (!reserva.getUsuario().getNome().equals(checkInDTO.getNomeCliente())) {
            throw new RuntimeException("O nome do cliente '" + checkInDTO.getNomeCliente() +
                    "' não confere com a reserva " + checkInDTO.getReservaId() + ".");
        }

        if (reserva.isCheckedIn()) {
            throw new RuntimeException("Check-in já realizado");
        }

        reserva.setCheckedIn(true);
        reservaService.atualizarReserva(reserva);

        return "Checkin realizado com sucesso";
    }

    public List<ReservaDTO> listarCheckins() {
      return  reservaService.buscarCheckins();
    }
}
