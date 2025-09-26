package br.com.ceub.gastroreserva.mapper;

import br.com.ceub.gastroreserva.dto.MesaDTO;
import br.com.ceub.gastroreserva.dto.ReservaDTO;
import br.com.ceub.gastroreserva.dto.RestauranteDTO;
import br.com.ceub.gastroreserva.entities.Restaurante;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RestauranteMapper {

    public static RestauranteDTO convertToDTO(Restaurante restaurante) {
        List<MesaDTO> mesas = new ArrayList<>();
        List<ReservaDTO> reservas = new ArrayList<>();
        if (Objects.nonNull(restaurante.getMesa())) {
            mesas = restaurante.getMesa().stream().map(MesaMapper::mesaToMesaDTO).toList();
        }

        if (Objects.nonNull(restaurante.getReserva())) {
            reservas = restaurante.getReserva().stream().map(ReservaMapper::toDTO).collect(Collectors.toList());
        }

        return RestauranteDTO
                .builder()
                .id(restaurante.getId())
                .nome(restaurante.getNome())
                .endereco(restaurante.getEndereco())
                .mesa(mesas)
                .reserva(reservas)
                .build();

    }

    public static Restaurante toRestaurante(RestauranteDTO restauranteDTO) {
        return Restaurante
                .builder()
                .nome(restauranteDTO.getNome())
                .endereco(restauranteDTO.getEndereco())
                .build();
    }

}
