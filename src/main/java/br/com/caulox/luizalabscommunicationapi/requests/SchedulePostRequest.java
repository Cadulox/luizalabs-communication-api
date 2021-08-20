package br.com.caulox.luizalabscommunicationapi.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulePostRequest {

    @Schema(description = "Campo de identificação do requisitante", example = "Fulano de Tal", required = true)
    @Size(min = 2, max = 100,message = "O requisitante precisa ter pelo menos 2 caracteres")
    @NotEmpty(message = "O requisitante não pode ser vazio")
    private String requester;

    @Schema(description = "Campo de data e hora para envio", example = "20/08/2022 18:00", required = true)
    @Pattern(regexp = "^(0[1-9]|([012][0-9])|(3[01]))/([0]?[1-9]|1[012])/\\d\\d\\d\\d [012]?[0-9]:[0-6][0-9]$",
            message = "Formato inválido para data e hora! Formato válido: dd/MM/yyyy HH:mm")
    @NotEmpty(message = "O campo data e hora de envio não pode estar vazio")
    private String sendDateTime;

    @Schema(description = "Campo tipo de mensagem a ser enviada", example = "WHATSAPP", required = true)
    @Size(min = 3, max = 8, message = "O tipo de mensagem precisa ter pelo menos 3 caracteres")
    @NotEmpty(message = "O tipo de mensagem não pode ser vazio")
    private String type;

    @Schema(description = "Campo de destinário da mensagem", example = "11911112222", required = true)
    @Size(min = 9, max = 50, message = "Tamanho inválido!")
    @NotEmpty(message = "O destinatário não pode ser vazio")
    private String receiver;

    @Schema(description = "Campo da mensagem a ser enviada", example = "Hello, World!", required = true)
    @Size(min = 1, max = 3000, message = "A mensagem precisa ter pelo menos 1 caractere")
    @NotEmpty(message = "O campo messagem não pode estar vazio")
    private String message;
}
