package br.com.ceub.gastroreserva.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckOutDTO {

    @NotBlank(message = "Obrigatório informar o nome do cliente")
    private String nomeCliente;

}
