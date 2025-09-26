package br.com.ceub.gastroreserva.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_reserva")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva extends Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USUARIO_COD")
    private Usuario usuario;

    @Column(name = "COMENTARIO")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "RESTAURANTE_COD")
    private Restaurante restaurante;

    @Column(name = "DATA_AGENDAMENTO")
    private LocalDateTime dataAgendamento;

    @OneToOne
    @JoinColumn(name = "MESA_COD")
    private Mesa mesa;

    @Column(name = "CHECKED_IN")
    private boolean checkedIn;

    @Column(name = "CHECKED_OUT")
    private boolean checkedOut;


}
