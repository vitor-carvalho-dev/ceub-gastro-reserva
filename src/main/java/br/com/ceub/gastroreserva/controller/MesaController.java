package br.com.ceub.gastroreserva.controller;

import br.com.ceub.gastroreserva.dto.MesaDTO;
import br.com.ceub.gastroreserva.services.MesaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final MesaService mesaService;

    @PostMapping
    public ResponseEntity<MesaDTO> save(@RequestBody @Valid MesaDTO mesaDTO) throws AccessDeniedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaService.salvarMesa(mesaDTO));
    }
}
