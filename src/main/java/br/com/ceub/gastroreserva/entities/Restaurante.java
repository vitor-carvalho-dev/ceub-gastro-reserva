package br.com.ceub.gastroreserva.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "tb_restaurante")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurante extends Auditoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD")
    private Long id;

    @Column(name = "NOME_RESTAURANTE")
    private String nome;

    @Column(name = "ENDERECO")
    private String endereco;

    @Column(name = "QTD_CADEIRA")
    @OneToMany(mappedBy = "restaurante")
    private List<Mesa> mesa;

    @Column(name = "COD_RESTAURANTE")
    @OneToMany(mappedBy = "restaurante")
    private List<Reserva> reserva;

    @Column(name = "CAMINHO_IMAGEM")
    private String caminhoImagem;

}
