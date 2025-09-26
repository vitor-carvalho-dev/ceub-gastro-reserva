package br.com.ceub.gastroreserva.entities;

import br.com.ceub.gastroreserva.enums.TipoNotificacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "tb_notificacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notificacao extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD")
    private Long id;

    @Column(name = "MENSAGEM", nullable = false)
    private String mensagem;

    @Column(name = "TIPO_NOTIFICACAO", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoNotificacao tipoNotificacao = TipoNotificacao.EMAIL;

    @ManyToOne
    @JoinColumn(name = "COD_USUARIO")
    private Usuario usuario;

}
