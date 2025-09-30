package br.com.ceub.gastroreserva.mapper;

import br.com.ceub.gastroreserva.dto.MesaDTO;
import br.com.ceub.gastroreserva.entities.Mesa;
import br.com.ceub.gastroreserva.entities.Restaurante;

public class MesaMapper {

    public static MesaDTO mesaToMesaDTO(Mesa mesa) {
        return MesaDTO
                .builder()
                .codMesa(mesa.getId())
                .qtdCadeira(mesa.getQtdCadeira())
                .codRestaurante(mesa.getRestaurante().getId())
                .numero(mesa.getNumero())
                .build();
    }

    public static Mesa toEntity(MesaDTO mesaDTO, Restaurante restaurante) {
        return Mesa
                .builder()
                .id(mesaDTO.getCodMesa())
                .qtdCadeira(mesaDTO.getQtdCadeira())
                .restaurante(restaurante)
                .numero(mesaDTO.getNumero())
                .build();
    }

}
