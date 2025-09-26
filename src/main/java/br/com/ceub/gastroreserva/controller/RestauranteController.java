package br.com.ceub.gastroreserva.controller;

import br.com.ceub.gastroreserva.dto.RestauranteDTO;
import br.com.ceub.gastroreserva.services.RestauranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    @Autowired
    private RestauranteService service;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid RestauranteDTO restauranteDTO) {
        if (service.restauranteJaExiste(restauranteDTO.getNome())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Restaurante já existe");
        }
        RestauranteDTO savedRestauranteDTO = service.save(restauranteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRestauranteDTO);
    }

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> listar() {
        return ResponseEntity.ok(service.buscarRestaurantes());
    }

}