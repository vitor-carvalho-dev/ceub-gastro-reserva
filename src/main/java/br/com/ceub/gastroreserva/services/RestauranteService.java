package br.com.ceub.gastroreserva.services;

import br.com.ceub.gastroreserva.dto.RestauranteDTO;
import br.com.ceub.gastroreserva.entities.Restaurante;
import br.com.ceub.gastroreserva.mapper.RestauranteMapper;
import br.com.ceub.gastroreserva.repository.MesaRepository;
import br.com.ceub.gastroreserva.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.ceub.gastroreserva.mapper.RestauranteMapper.convertToDTO;
import static br.com.ceub.gastroreserva.mapper.RestauranteMapper.toRestaurante;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final MesaRepository mesaRepository;

    public RestauranteDTO save(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = toRestaurante(restauranteDTO);
        restaurante = restauranteRepository.save(restaurante);
        return convertToDTO(restaurante);
    }


    public boolean restauranteJaExiste(String nome) {
        return restauranteRepository.existsByNome(nome);
    }


    public List<RestauranteDTO> buscarRestaurantes() {
        return restauranteRepository.findAll()
                .stream()
                .map(RestauranteMapper::convertToDTO)
                .toList();

    }
}
