package br.com.ceub.gastroreserva.services;

import br.com.ceub.gastroreserva.dto.MesaDTO;
import br.com.ceub.gastroreserva.entities.Mesa;
import br.com.ceub.gastroreserva.entities.Restaurante;
import br.com.ceub.gastroreserva.exceptions.DataIntegrityViolation;
import br.com.ceub.gastroreserva.exceptions.RecursoNaoEncontradoException;
import br.com.ceub.gastroreserva.mapper.MesaMapper;
import br.com.ceub.gastroreserva.repository.MesaRepository;
import br.com.ceub.gastroreserva.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;

@Service
@RequiredArgsConstructor
public class MesaService {

    private final RestauranteRepository restauranteRepository;

    private final MesaRepository mesaRepository;

    private final AutenticacaoService autenticacaoService;


    public MesaDTO salvarMesa(MesaDTO mesaDTO) throws AccessDeniedException {
        try {
            if (!autenticacaoService.verificarSeUsuarioEstaAutenticado()) {
                throw new AccessDeniedException("Acesso negado. Usuário não esta autenticado");
            }

            Restaurante restaurante = restauranteRepository.findById(mesaDTO.getCodRestaurante())
                    .orElseThrow(() -> new RecursoNaoEncontradoException(String.format("Restaurante com id:%d não encontrado", mesaDTO.getCodRestaurante())));

            Mesa entity = MesaMapper.toEntity(mesaDTO, restaurante);

            Mesa mesaCadastrada = mesaRepository.save(entity);
            return MesaMapper.mesaToMesaDTO(mesaCadastrada);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolation(String.format("Mesa com  id:%d ja cadastrada", mesaDTO.getCodMesa()), ex);
        }
    }
}