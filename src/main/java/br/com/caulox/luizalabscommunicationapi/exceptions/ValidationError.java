package br.com.caulox.luizalabscommunicationapi.exceptions;

import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
public class ValidationError extends StandardError {

    private final List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String msg, LocalDateTime timestamp) {
        super(status, msg, timestamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
