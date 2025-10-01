package br.com.ceub.gastroreserva.controller;


import br.com.ceub.gastroreserva.dto.CheckOutDTO;
import br.com.ceub.gastroreserva.services.CheckOutService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkouts")
@RequiredArgsConstructor
@Tag(name = "Check-out", description = "Finaliza a permanência de um cliente no restaurante.")

public class CheckOutController {

    private final CheckOutService service;

    @PostMapping
    @Operation(summary = "Realizar Check-out", description = "Finaliza a reserva, liberando a mesa e encerrando a reserva do cliente.")
    public ResponseEntity<?> salvar(@RequestBody @Valid CheckOutDTO checkOutDTO) {
        String checkedOutDTO = service.realizarCheckOut(checkOutDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(checkedOutDTO);
    }
}