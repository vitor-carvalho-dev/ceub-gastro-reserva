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
import java.nio.file.Paths; // <<< IMPORTAÇÃO NECESSÁRIA
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
    private final Path rootLocation = Paths.get("uploads"); // Define o caminho para a pasta 'uploads' na raiz do projeto.

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

    @Transactional // Garante que ou tudo funciona, ou nada é salvo (consistência)
    public void salvarImagemRestaurante(Long id, MultipartFile file) {
        try {
            // Passo 3.1: Garantir que a pasta de uploads exista. Se não, crie-a.
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }

            // Passo 3.2: Buscar o restaurante pelo ID. Se não encontrar, lança uma exceção.
            Restaurante restaurante = restauranteRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Restaurante com ID " + id + " não encontrado."));

            // Passo 3.3: Gerar um nome de arquivo único para evitar conflitos.
            // Pega a extensão do arquivo original (ex: .png, .jpg)
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            // Cria o nome final: um UUID aleatório + extensão (ex: 550e8400-e29b-41d4-a716-446655440000.jpg)
            String uniqueFilename = UUID.randomUUID().toString() + extension;

            // Passo 3.4: Salvar o arquivo no sistema de arquivos.
            Path destinationFile = rootLocation.resolve(uniqueFilename).normalize().toAbsolutePath();
            try (InputStream inputStream = file.getInputStream()) {
                // Copia o conteúdo do arquivo enviado para o nosso arquivo de destino
                // REPLACE_EXISTING garante que ele sobrescreva se um arquivo com o mesmo UUID (improvável) já existir
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            // Passo 3.5: Atualizar a entidade com o nome do novo arquivo.
            restaurante.setCaminhoImagem(uniqueFilename);

            // Passo 3.6: Salvar a entidade atualizada no banco de dados.
            restauranteRepository.save(restaurante);

        } catch (IOException e) {
            // Se ocorrer um erro durante a manipulação do arquivo, lança uma exceção
            // O @Transactional fará o rollback de qualquer mudança no banco de dados
            throw new RuntimeException("Falha ao salvar o arquivo da imagem.", e);
        }
    }
}
