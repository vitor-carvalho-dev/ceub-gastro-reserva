package br.com.ceub.gastroreserva.mapper;

import br.com.ceub.gastroreserva.dto.UsuarioDTO;
import br.com.ceub.gastroreserva.entities.TermoAceite;
import br.com.ceub.gastroreserva.entities.Usuario;

import java.util.Objects;


public class UsuarioMapper {

    public static Usuario toEntity(UsuarioDTO usuarioDTO, TermoAceite termoAceite) {
        return Usuario
                .usuarioBuilder()
                .tipoUsuario(usuarioDTO.getTipoUsuario())
                .cpf(usuarioDTO.getCpf())
                .nome(usuarioDTO.getNome())
                .senha(usuarioDTO.getSenha())
                .email(usuarioDTO.getEmail())
                .endereco(usuarioDTO.getEndereco())
                .termoAceite(termoAceite)
                .build();
    }

    public static UsuarioDTO toDTO(Usuario usuario) {
        return UsuarioDTO
                .builder()
                .id(usuario.getId())
                .tipoUsuario(usuario.getTipoUsuario())
                .cpf(usuario.getCpf())
                .nome(usuario.getNome())
                .senha(usuario.getSenha())
                .email(usuario.getEmail())
                .endereco(usuario.getEndereco())
                .codTermoAceite(usuario.getTermoAceite().getId())
                .build();
    }

    public static Usuario toUpdate(Usuario usuarioExistente, Usuario entity) {
        return Usuario
                .usuarioBuilderSuper()
                .id(usuarioExistente.getId())
                .nome(Objects.nonNull(entity.getNome()) ? entity.getNome() : usuarioExistente.getNome())
                .senha(Objects.nonNull(entity.getSenha()) ? entity.getSenha() : usuarioExistente.getSenha())
                .cpf(usuarioExistente.getCpf())
                .tipoUsuario(Objects.nonNull(entity.getTipoUsuario()) ? entity.getTipoUsuario() : usuarioExistente.getTipoUsuario())
                .endereco(Objects.nonNull(entity.getEndereco()) ? entity.getEndereco() : usuarioExistente.getEndereco())
                .email(Objects.nonNull(entity.getEmail()) ? entity.getEmail() : usuarioExistente.getEmail())
                .termoAceite(usuarioExistente.getTermoAceite())
                .dataDeInclusao(usuarioExistente.getDataDeInclusao())
                .build();
    }
}
