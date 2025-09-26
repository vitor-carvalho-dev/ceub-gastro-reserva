package br.com.ceub.gastroreserva.dto;

import br.com.ceub.gastroreserva.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
@Builder
public class UsuarioDTO {

    private Long id;

    @NotNull(message = "Campo tipoUsuario obrigatório")
    private TipoUsuario tipoUsuario;

    @NotBlank(message = "Campo  cpf obrigatório")
    @CPF
    private String cpf;

    @NotBlank(message = "Campo nome obrigatório")
    private String nome;

    @NotBlank(message = "Campo senha obrigatório")
    private String senha;

    @Email
    @NotBlank(message = "Campo email obrigatório")
    private String email;

    @NotBlank(message = "Campo endereco obrigatório")
    private String endereco;

    @NotNull(message = "Campo codTermoAceite obrigatório")
    private Long codTermoAceite;



}
