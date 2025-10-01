package br.com.ceub.gastroreserva.dto;

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
public class CheckInDTO {

    @NotNull(message = "O ID da reserva é obrigatório para o check-in")
    private Long reservaId;
    @NotBlank(message = "Campo nome do cliente obrigatório")
    private String nomeCliente;
    private int numeroMesa;

}
