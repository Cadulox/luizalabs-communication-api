package br.com.caulox.luizalabscommunicationapi.requests;

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

    @Size(min = 2, message = "O status precisa ter pelo menos 2 caracteres")
    @NotEmpty(message = "O status não pode ser vazio")
    private String status;
}
