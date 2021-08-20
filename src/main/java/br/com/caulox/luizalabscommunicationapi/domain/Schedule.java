package br.com.caulox.luizalabscommunicationapi.domain;

import br.com.caulox.luizalabscommunicationapi.domain.enums.Status;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Schedule {

    @Schema(description = "Identificador do agendamento", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Campo de identificação do requisitante", example = "Fulano de Tal")
    @Column(nullable = false)
    private String requester;

    @Schema(description = "Campo de data e hora para envio", example = "26/08/2022 18:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(nullable = false)
    private LocalDateTime sendDateTime;

    @Schema(description = "Campo tipo de mensagem a ser enviada", example = "EMAIL")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;

    @Schema(description = "Campo de destinário da mensagem", example = "fulano@email.com.br")
    @Column(nullable = false)
    private String receiver;

    @Schema(description = "Campo da mensagem a ser enviada", example = "Hello, World!")
    @Column(nullable = false)
    private String message;

    @Schema(description = "Campo de data e hora de criação do agendamento", example = "20/08/2022 18:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Schema(description = "Campo de data e hora de alteração do agendamento", example = "23/08/2022 18:00")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Schema(description = "Campo de status do agendamento", example = "PENDING")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
