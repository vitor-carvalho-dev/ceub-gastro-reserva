package br.com.ceub.gastroreserva.services;
import br.com.ceub.gastroreserva.dto.RestauranteDTO;
import br.com.ceub.gastroreserva.entities.Restaurante;
import br.com.ceub.gastroreserva.mapper.RestauranteMapper;
import br.com.ceub.gastroreserva.repository.MesaRepository;
import br.com.ceub.gastroreserva.repository.RestauranteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import static br.com.ceub.gastroreserva.mapper.RestauranteMapper.convertToDTO;
import static br.com.ceub.gastroreserva.mapper.RestauranteMapper.toRestaurante;

@Service
@RequiredArgsConstructor
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final MesaRepository mesaRepository;
    private final Path rootLocation = Paths.get("uploads");

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

    @Transactional
    public void salvarImagemRestaurante(Long id, MultipartFile file) {
        try {

            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            Restaurante restaurante = restauranteRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Restaurante com ID " + id + " não encontrado."));

            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String uniqueFilename = UUID.randomUUID().toString() + extension;

            Path destinationFile = rootLocation.resolve(uniqueFilename).normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            restaurante.setCaminhoImagem(uniqueFilename);
            restauranteRepository.save(restaurante);

        } catch (IOException e) {
            throw new RuntimeException("Falha ao salvar o arquivo da imagem.", e);
        }
    }

    public String buscarCaminhoImagem(Long id) {
        return restauranteRepository.findById(id)
                .map(Restaurante::getCaminhoImagem)
                .orElse(null);
    }
}
