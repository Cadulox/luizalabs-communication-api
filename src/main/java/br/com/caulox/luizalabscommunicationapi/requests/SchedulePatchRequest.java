package br.com.caulox.luizalabscommunicationapi.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchedulePatchRequest {

    @Schema(description = "Campo de status do agendamento", example = "CANCELED", required = true)
    @Size(min = 4, max = 30,message = "O status precisa ter pelo menos 4 caracteres")
    @NotEmpty(message = "O status não pode ser vazio")
    private String status;
}
