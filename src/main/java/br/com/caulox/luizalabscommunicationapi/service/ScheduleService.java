package br.com.caulox.luizalabscommunicationapi.service;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Status;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Type;
import br.com.caulox.luizalabscommunicationapi.exception.ObjectNotFoundException;
import br.com.caulox.luizalabscommunicationapi.repository.ScheduleRepository;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public Schedule save(SchedulePostRequest schedulePostRequest) {

        LocalDateTime sendDateTime = LocalDateTime
                .parse(schedulePostRequest.getSendDateTime(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        Type typeMessage = Type.valueOf(schedulePostRequest.getType().toUpperCase());

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
}
