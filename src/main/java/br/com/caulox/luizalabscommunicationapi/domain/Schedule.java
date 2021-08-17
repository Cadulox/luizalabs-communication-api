package br.com.caulox.luizalabscommunicationapi.domain;

import br.com.caulox.luizalabscommunicationapi.domain.enums.Status;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requester;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime shippingDateTime;

    @Enumerated(EnumType.STRING)
    private Type type;

    private String message;

    @Enumerated(EnumType.STRING)
    private Status status;
}
