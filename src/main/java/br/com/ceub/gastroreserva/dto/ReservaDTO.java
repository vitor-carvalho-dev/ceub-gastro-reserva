package br.com.ceub.gastroreserva.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservaDTO {

    @NotNull(message = "Campo codRestaurante obrigatório")
    private Long codRestaurante;

    @NotNull(message = "Campo  codUsuario obrigatório")
    private Long codUsuario;

    @NotNull(message = "Campo codMesa obrigatório")
    private Long codMesa;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @NotNull(message = "Campo dataAgendamento obrigatório")
    private LocalDateTime dataAgendamento;

    private String comentario;

    @Schema(hidden = true)
    private String nomeCliente;

    @Schema(hidden = true)
    private boolean checkedIn;


}
