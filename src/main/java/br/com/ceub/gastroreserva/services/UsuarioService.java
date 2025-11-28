package br.com.ceub.gastroreserva.services;

import br.com.ceub.gastroreserva.dto.UsuarioDTO;
import br.com.ceub.gastroreserva.entities.TermoAceite;
import br.com.ceub.gastroreserva.entities.Usuario;
import br.com.ceub.gastroreserva.exceptions.RecursoNaoEncontradoException;
import br.com.ceub.gastroreserva.mapper.UsuarioMapper;
import br.com.ceub.gastroreserva.repository.TermoAceiteRepository;
import br.com.ceub.gastroreserva.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final TermoAceiteRepository termoAceiteRepository;
    private final AutenticacaoService autenticacaoService;

    @Transactional
    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) throws AccessDeniedException {
        if (!autenticacaoService.verificarSeUsuarioEstaAutenticado())  {
            throw new AccessDeniedException("Acesso negado. Usuário não esta autenticado");
        }

        // Verifica se já existe um usuário com o mesmo CPF
        usuarioRepository.findByCpf(usuarioDTO.getCpf()).ifPresent(u -> {
            throw new DataIntegrityViolationException("CPF " + usuarioDTO.getCpf() + " já cadastrado no sistema.");
        });

        // Verifica se o termo de aceite existe
        TermoAceite termoAceite = termoAceiteRepository.findById(usuarioDTO.getCodTermoAceite())
                .orElseThrow(() -> new RecursoNaoEncontradoException(String.format("Termo de aceite com id:%d não encontrado", usuarioDTO.getCodTermoAceite())));

        // Converte o DTO para a entidade e salva
        Usuario entity = UsuarioMapper.toEntity(usuarioDTO, termoAceite);
        Usuario usuarioSalvo = usuarioRepository.save(entity);

        return UsuarioMapper.toDTO(usuarioSalvo);
    }


    public List<UsuarioDTO> listarUsuarios() {
       return  usuarioRepository.findAll().stream().map(UsuarioMapper::toDTO).toList();
    }

    public UsuarioDTO buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioMapper::toDTO)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado."));
    }

    @Transactional
    public UsuarioDTO atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado para atualização."));

        TermoAceite termoAceite = termoAceiteRepository.findById(usuarioDTO.getCodTermoAceite())
                .orElseThrow(() -> new RecursoNaoEncontradoException(String.format("Termo de aceite com id:%d não encontrado", usuarioDTO.getCodTermoAceite())));

        // Atualiza os dados do usuário existente
        usuarioExistente.setNome(usuarioDTO.getNome());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuarioExistente.setEndereco(usuarioDTO.getEndereco());
        usuarioExistente.setTipoUsuario(usuarioDTO.getTipoUsuario());
        usuarioExistente.setTermoAceite(termoAceite); // se precisar para o termo de aceite

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return UsuarioMapper.toDTO(usuarioAtualizado);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Usuário com ID " + id + " não encontrado para exclusão.");
        }
        try {
            usuarioRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Não é possível deletar o usuário ID " + id + " pois ele possui dados relacionados (reservas, etc).");
        }
    }

}
