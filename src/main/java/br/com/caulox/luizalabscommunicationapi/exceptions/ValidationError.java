package br.com.caulox.luizalabscommunicationapi.exceptions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(Integer status, String msg, LocalDateTime timestamp) {
        super(status, msg, timestamp);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }

    public void addError(String fieldName, String message) {
        erros.add(new FieldMessage(fieldName, message));
    }
}
