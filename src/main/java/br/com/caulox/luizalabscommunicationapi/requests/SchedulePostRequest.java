package br.com.caulox.luizalabscommunicationapi.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class SchedulePostRequest {

    @Size(min = 2, message = "O requisitante precisa ter pelo menos 2 caracteres")
    @NotEmpty(message = "O requisitante não pode ser vazio")
    private String requester;

    @Pattern(regexp = "^(0[1-9]|([012][0-9])|(3[01]))/([0]?[1-9]|1[012])/\\d\\d\\d\\d [012]?[0-9]:[0-6][0-9]$",
            message = "Formato inválido para data e hora!")
    private String sendDateTime;

    @Size(min = 2, message = "O tipo de mensagem precisa ter pelo menos 2 caracteres")
    @NotEmpty(message = "O tipo de mensagem não pode ser vazio")
    private String type;

    @Size(min = 11, message = "Formato inválido!")
    @NotEmpty(message = "O destinatário não pode ser vazio")
    private String receiver;

    @Size(min = 1, message = "A mensagem precisa ter pelo menos 1 caractere")
    private String message;
}
