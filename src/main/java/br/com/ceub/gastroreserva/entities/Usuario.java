package br.com.ceub.gastroreserva.entities;

import br.com.ceub.gastroreserva.enums.TipoUsuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "tb_usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "usuarioBuilder")
@EqualsAndHashCode(callSuper = true)
public class Usuario extends Auditoria {

  @Builder(builderMethodName = "usuarioBuilderSuper")
    public Usuario(LocalDateTime dataDeInclusao, LocalDateTime dataDeAlteracao, Long id, String cpf, TipoUsuario tipoUsuario, String nome, String senha, String email, String endereco, TermoAceite termoAceite) {
        super(dataDeInclusao, dataDeAlteracao);
        this.id = id;
        this.cpf = cpf;
        this.tipoUsuario = tipoUsuario;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.endereco = endereco;
        this.termoAceite = termoAceite;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD")
    private Long id;

    @Column(name = "TIPO_USUARIO", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "SENHA", nullable = false)
    private String senha;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "ENDERECO", nullable = false)
    private String endereco;

    @OneToOne(cascade = CascadeType.ALL)
    private TermoAceite termoAceite;
}
