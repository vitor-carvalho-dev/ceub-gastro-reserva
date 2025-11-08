package br.com.ceub.gastroreserva.controller;

import br.com.ceub.gastroreserva.dto.RestauranteDTO;
import br.com.ceub.gastroreserva.services.RestauranteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
@Tag(name = "Restaurante", description = "Cadastro e gestão de estabelecimentos.")
public class RestauranteController {

    private final RestauranteService service;
    private final Path rootLocation = Paths.get("uploads");

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

    @PostMapping(value = "/{id}/imagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImagem(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo enviado está vazio.");
        }
        try {
            service.salvarImagemRestaurante(id, file);
            return ResponseEntity.ok().body("Upload da imagem para o restaurante ID " + id + " realizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar o upload da imagem: " + e.getMessage());
        }
    }


    @GetMapping("/{id}/imagem")
    public ResponseEntity<Resource> getImagem(@PathVariable Long id) {
        try {
            String nomeArquivo = service.buscarCaminhoImagem(id);
            if (nomeArquivo == null || nomeArquivo.isBlank()) {
                return ResponseEntity.notFound().build();
            }

            Path arquivo = rootLocation.resolve(nomeArquivo);
            Resource resource = new UrlResource(arquivo.toUri());

            if (resource.exists() && resource.isReadable()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}