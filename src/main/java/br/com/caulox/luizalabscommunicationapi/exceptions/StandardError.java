package br.com.caulox.luizalabscommunicationapi.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {

    private Integer status;
    private String msg;
    private LocalDateTime timestamp;
}
