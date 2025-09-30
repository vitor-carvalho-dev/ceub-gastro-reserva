package br.com.ceub.gastroreserva.services;

import br.com.ceub.gastroreserva.dto.ReservaDTO;
import br.com.ceub.gastroreserva.entities.Reserva;
import br.com.ceub.gastroreserva.entities.Restaurante;
import br.com.ceub.gastroreserva.entities.Usuario;
import br.com.ceub.gastroreserva.exceptions.DataIntegrityViolation;
import br.com.ceub.gastroreserva.exceptions.RecursoNaoEncontradoException;
import br.com.ceub.gastroreserva.mapper.ReservaMapper;
import br.com.ceub.gastroreserva.repository.ReservaRepository;
import br.com.ceub.gastroreserva.repository.RestauranteRepository;
import br.com.ceub.gastroreserva.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservaService {

    private final ReservaRepository reservaRepository;

    private final RestauranteRepository restauranteRepository;

    private final UsuarioRepository usuarioRepository;

    private final AutenticacaoService autenticacaoService;

    private final NotificacaoService notificacaoService;

    @Transactional(rollbackOn = Exception.class)

    public ReservaDTO salvarReserva(@Valid ReservaDTO reservaDTO) {
        try {
            // 🔹 Se quiser validar manualmente a autenticação, descomente:
            // if (!autenticacaoService.verificarSeUsuarioEstaAutenticado()) {
            //     throw new RuntimeException("Acesso negado. Usuário não está autenticado");
            // }

            Usuario usuario = usuarioRepository.findById(reservaDTO.getCodUsuario())
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                            String.format("Usuário com id:%d não encontrado", reservaDTO.getCodUsuario())));

            Restaurante restaurante = restauranteRepository.findById(reservaDTO.getCodRestaurante())
                    .orElseThrow(() -> new RecursoNaoEncontradoException(
                            String.format("Restaurante com id:%d não encontrado", reservaDTO.getCodRestaurante())));

            var mesaSelecionada = restaurante.getMesa().stream()
                    .filter(mesa -> Objects.equals(mesa.getId(), reservaDTO.getCodMesa()))
                    .findFirst();

            if (mesaSelecionada.isEmpty()) {
                throw new RuntimeException(
                        String.format("Mesa com id:%d não localizada", reservaDTO.getCodMesa()));
            }

            Reserva entity = ReservaMapper.toEntity(reservaDTO, usuario, restaurante, mesaSelecionada);

            boolean existeReserva = reservaRepository.existeReserva(entity.getUsuario().getCpf(),
                    entity.getRestaurante().getId());
            if (existeReserva) {
                throw new RuntimeException(
                        String.format("Reserva já existe para o usuário: %s nesse restaurante: %s. Favor selecionar outro!",
                                entity.getUsuario().getNome(), entity.getRestaurante().getNome()));
            }

            Reserva reservaSalva = reservaRepository.save(entity);

          //  notificacaoService.enviarNotificacao(usuario,
           //         String.format("%s, sua reserva para o restaurante %s foi efetuada com sucesso.",
            //                usuario.getNome(), restaurante.getNome()));

            String mensagem = "%s, sua reserva para o restaurante %s foi efetuada com sucesso.";

            try {
                notificacaoService.enviarNotificacao(usuario, mensagem);
            } catch (Exception e) {
                log.error("Falha ao enviar notificação: {}", e.getMessage());
            }

            return ReservaMapper.toDTO(reservaSalva);

        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolation(
                    String.format("Mesa com id:%d já agendada. Selecione outra", reservaDTO.getCodMesa()), ex);
        }
    }

    public Reserva buscarReservaPorNomeCliente(String nomeCliente) {
        return reservaRepository.findByUsuario_Nome(nomeCliente).orElseThrow(() ->
                new RecursoNaoEncontradoException("Cliente não possui reserva!"));

    }

    public void atualizarReserva(Reserva reserva) {
        reservaRepository.save(reserva);
    }

    public List<ReservaDTO> listaReserva() {
        return reservaRepository.findAll().stream().map(ReservaMapper::toDTO).toList();
    }

    public List<ReservaDTO> buscarCheckins() {
        return reservaRepository.findAll().stream().map(ReservaMapper::toDTO).filter(reservaDTO -> reservaDTO.isCheckedIn()).toList();
    }
}
