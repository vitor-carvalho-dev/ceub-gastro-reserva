package br.com.ceub.gastroreserva.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dados necessários para realizar o Check-out de uma reserva.")
public class CheckOutDTO {

    @NotNull(message = "O ID da reserva é obrigatório para o check-out")
    @Schema(description = "ID único da reserva a ser finalizada (reservas).", example = "5")
    private Long reservaId;

    @NotBlank(message = "Obrigatório informar o nome do cliente")
    @Schema(description = "Nome do cliente para conferência da reserva.", example = "Icara Barracada")
    private String nomeCliente;

}
