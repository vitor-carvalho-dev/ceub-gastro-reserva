package br.com.ceub.gastroreserva.controller;


import br.com.ceub.gastroreserva.dto.CheckOutDTO;
import br.com.ceub.gastroreserva.services.CheckOutService;
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
public class CheckOutController {

    private final CheckOutService service;

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid CheckOutDTO checkOutDTO) {
            String checkedOutDTO = service.realizarCheckOut(checkOutDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(checkedOutDTO);
    }
}