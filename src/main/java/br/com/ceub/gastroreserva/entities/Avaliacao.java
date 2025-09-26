package br.com.ceub.gastroreserva.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Table(name = "tb_avaliacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao extends Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD")
    private Long id;

    @Column(name = "NOTA", nullable = false)
    private Integer nota;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COD_RESERVA")
    private Reserva reserva;

    @OneToOne
    @JoinColumn(name = "COD_NOTIFICACAO")
    private Notificacao notificacao;
}
