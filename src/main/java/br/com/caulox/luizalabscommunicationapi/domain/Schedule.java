package br.com.caulox.luizalabscommunicationapi.domain;

import br.com.caulox.luizalabscommunicationapi.domain.enums.Status;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requester;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime sendDateTime;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String receiver;
    private String message;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private Status status;
}
