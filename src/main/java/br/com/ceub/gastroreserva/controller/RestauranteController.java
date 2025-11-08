package br.com.ceub.gastroreserva.controller;

import br.com.ceub.gastroreserva.dto.RestauranteDTO;
import br.com.ceub.gastroreserva.services.RestauranteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
@Tag(name = "Restaurante", description = "Cadastro e gestão de estabelecimentos.")
public class RestauranteController {

   // @Autowired
    // private RestauranteService service;
    private final RestauranteService service;

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

    /**
     * NOVO ENDPOINT: Endpoint para fazer o upload da imagem de um restaurante específico.
     * @param id O ID do restaurante ao qual a imagem pertence.
     * @param file O arquivo da imagem enviado na requisição (multipart/form-data).
     * @return Uma resposta de sucesso ou erro.
     **/

    @PostMapping(value = "/{id}/imagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadImagem(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        // Validação básica para garantir que o arquivo não está vazio
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O arquivo enviado está vazio.");
        }

        try {
            // Delega a lógica de salvar a imagem para a camada de serviço
            service.salvarImagemRestaurante(id, file);
            return ResponseEntity.ok().body("Upload da imagem para o restaurante ID " + id + " realizado com sucesso.");
        } catch (Exception e) {
            // É uma boa prática logar o erro no servidor
            // log.error("Erro ao fazer upload de imagem para restaurante ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar o upload da imagem: " + e.getMessage());
        }
    }
}