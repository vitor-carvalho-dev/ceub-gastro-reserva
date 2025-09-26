package br.com.ceub.gastroreserva.exceptions;

import lombok.Data;
import org.springframework.dao.DataIntegrityViolationException;

@Data
public class DataIntegrityViolation extends RuntimeException {

    private DataIntegrityViolationException dataIntegrityViolationException;

    public DataIntegrityViolation(String message, DataIntegrityViolationException e) {
        super(message);
        this.dataIntegrityViolationException = e;
    }
}
