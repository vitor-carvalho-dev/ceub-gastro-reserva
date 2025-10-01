package br.com.ceub.gastroreserva.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Dados necessários para realizar o cadastro da mesa.")
public class MesaDTO {

    @NotNull(message = "Campo codRestaurante obrigatório")
    private Long codRestaurante;

    @NotNull(message = "Campo codMesa obrigatório")
    private Long codMesa;

    @NotNull(message = "Campo qtdCadeira obrigatório")
    private Integer qtdCadeira;

    @NotNull(message = "Campo qtdCadeira obrigatório")
    private int numero ;
}
