package br.com.ceub.gastroreserva.controller;

import br.com.ceub.gastroreserva.dto.CheckInDTO;
import br.com.ceub.gastroreserva.dto.ReservaDTO;
import br.com.ceub.gastroreserva.services.CheckInService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkins")
@RequiredArgsConstructor
@Tag(name = "Check-in", description = "Registrar a chegada do cliente no restaurante.")
public class CheckInController {

    private final CheckInService service;

    @PostMapping
    public ResponseEntity<?> checkIn(@RequestBody @Valid CheckInDTO checkInDTO) {

        String response = service.realizarCheckin(checkInDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listar() {
        return ResponseEntity.ok(service.listarCheckins());
    }
}
