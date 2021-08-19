package br.com.caulox.luizalabscommunicationapi.util;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Status;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Type;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ScheduleCreator {

    public static Schedule createScheduleToBeSaved() {
        return Schedule.builder()
                .requester("Fulano")
                .sendDateTime(LocalDateTime.of(2021, 9, 18, 21, 0))
                .type(Type.EMAIL)
                .receiver("fulano@email.com.br")
                .message("Hello, World")
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .status(Status.PENDING)
                .build();
    }

    public static Schedule createValidSchedule() {
        return Schedule.builder()
                .id(1L)
                .requester("Fulano")
                .sendDateTime(LocalDateTime.of(2021, 9, 18, 21, 0))
                .type(Type.EMAIL)
                .receiver("fulano@email.com.br")
                .message("Hello, World")
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .status(Status.PENDING)
                .build();
    }

    public static Schedule createValidUpdatedSchedule() {
        return Schedule.builder()
                .id(1L)
                .requester("Fulano")
                .sendDateTime(LocalDateTime.of(2021, 9, 18, 21, 0))
                .type(Type.EMAIL)
                .receiver("fulano@email.com.br")
                .message("Hello, World")
                .createdAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .updatedAt(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .status(Status.CANCELED)
                .build();
    }
}
