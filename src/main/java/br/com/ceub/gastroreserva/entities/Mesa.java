package br.com.ceub.gastroreserva.entities;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Entity
@Table(name = "tb_mesa")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mesa extends Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD")
    private Long id;


    @Column(name = "QTD_CADEIRA", nullable = false)
    private Integer qtdCadeira;

    @ManyToOne
    @JoinColumn(name = "COD_RESTAURANTE", nullable = false)
    private Restaurante restaurante;

    @Column(name = "NUMERO", nullable = false)
    private int numero;

}
