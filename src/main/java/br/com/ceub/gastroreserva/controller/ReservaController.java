package br.com.ceub.gastroreserva.controller;

import br.com.ceub.gastroreserva.dto.ReservaDTO;
import br.com.ceub.gastroreserva.services.ReservaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService reservaService;

    @PostMapping
    public ResponseEntity<ReservaDTO> incluir(@RequestBody @Valid ReservaDTO reservaDTO) throws AccessDeniedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.salvarReserva(reservaDTO));
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listar() {
        return ResponseEntity.ok(reservaService.listaReserva());
    }
}
