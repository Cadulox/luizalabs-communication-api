package br.com.caulox.luizalabscommunicationapi.service;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Status;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Type;
import br.com.caulox.luizalabscommunicationapi.exceptions.ObjectNotFoundException;
import br.com.caulox.luizalabscommunicationapi.repository.ScheduleRepository;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Schedule save(SchedulePostRequest schedulePostRequest) {
        LocalDateTime sendDateTime = LocalDateTime
                .parse(schedulePostRequest.getSendDateTime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        if (sendDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("A data e hora não pode ser anterior a atual!");
        }

        if (!containsEnum(schedulePostRequest.getType().toUpperCase())) {
            throw new IllegalArgumentException("Tipo inválido de mensagem! Tipos permitidos: "
                    + Arrays.asList(Type.values()));
        }

        Type typeMessage = Type.valueOf(schedulePostRequest.getType().toUpperCase());

        if (typeMessage.equals(Type.EMAIL)) {
            isValidEmailAddress(schedulePostRequest.getReceiver());
        }

        Schedule schedule = Schedule
                .builder()
                .requester(schedulePostRequest.getRequester())
                .sendDateTime(sendDateTime)
                .type(typeMessage)
                .receiver(schedulePostRequest.getReceiver())
                .message(schedulePostRequest.getMessage())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(Status.PENDING)
                .build();

        return scheduleRepository.save(schedule);
    }

    public Schedule findByIdOrThrowBadRequestException(Long id) {
        return scheduleRepository
                .findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Agendamento não encontrado! Id: " + id));
    }

    private boolean containsEnum(String str) {
        for (Type t : Type.values()) {
            if (t.name().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void isValidEmailAddress(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            throw new IllegalArgumentException("E-mail inválido!");
        }
    }
}
