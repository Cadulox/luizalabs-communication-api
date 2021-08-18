package br.com.caulox.luizalabscommunicationapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StandardError {

    private Integer status;
    private String msg;
    private LocalDateTime timestamp;
}
