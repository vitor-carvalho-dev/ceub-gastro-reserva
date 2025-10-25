package br.com.ceub.gastroreserva.controller;

import br.com.ceub.gastroreserva.dto.UsuarioDTO;
import br.com.ceub.gastroreserva.services.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;


@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e gestão de clientes e administradores do sistema.")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> save(@RequestBody @Valid UsuarioDTO usuarioDTO) throws AccessDeniedException {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.salvarUsuario(usuarioDTO));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }
}
