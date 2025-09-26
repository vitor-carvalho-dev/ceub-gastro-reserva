package br.com.ceub.gastroreserva.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Auditoria {

    private LocalDateTime dataDeInclusao;

    private LocalDateTime dataDeAlteracao;

    @PrePersist
    protected void onCreate() {
            this.dataDeInclusao = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.dataDeAlteracao = LocalDateTime.now();
    }
}
