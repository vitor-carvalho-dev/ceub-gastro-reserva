package br.com.ceub.gastroreserva.services;

import br.com.ceub.gastroreserva.dto.CheckOutDTO;
import br.com.ceub.gastroreserva.entities.Reserva;
import br.com.ceub.gastroreserva.exceptions.RecursoNaoEncontradoException; // Importar
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckOutService {

    private final ReservaService reservaService;
    private final NotificacaoService notificacaoService;

    public String realizarCheckOut(CheckOutDTO checkOutDTO) {

        // Buscar a Reserva pelo ID
        Reserva reserva = reservaService.buscarReservaPorId(checkOutDTO.getReservaId())
                .orElseThrow(() -> new RecursoNaoEncontradoException(
                        "Reserva com ID " + checkOutDTO.getReservaId() + " não encontrada."));

        // Validação: Checar se o nome do cliente confere
        if (!reserva.getUsuario().getNome().equals(checkOutDTO.getNomeCliente())) {
            throw new RuntimeException("O nome do cliente não confere com a reserva informada.");
        }

        // Validação: A reserva deve ter o Check-in realizado
        if (!reserva.isCheckedIn()) {
            throw new RuntimeException("Check-out negado. O Check-in para esta reserva ainda não foi realizado.");
        }

        // Validação: Checar se o Check-out não foi realizado
        if (reserva.isCheckedOut()) {
            throw new RuntimeException("Check-out já realizado para esta reserva.");
        }

        // Atualização do estado
        reserva.setCheckedOut(true);
        reservaService.atualizarReserva(reserva);

        // Envio de Notificação (com tratamento de erro, se necessário)
        try {
            notificacaoService.enviarNotificacao(reserva.getUsuario(),
                    "Seu Check-out foi registrado. Esperamos vê-lo novamente!");
        } catch (Exception e) {
            System.err.println("Falha ao enviar notificação de Check-out: " + e.getMessage());
        }

        return "Check-out realizado com sucesso!";
    }
}