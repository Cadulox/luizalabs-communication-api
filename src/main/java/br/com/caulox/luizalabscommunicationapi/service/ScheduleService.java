package br.com.caulox.luizalabscommunicationapi.service;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Status;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Type;
import br.com.caulox.luizalabscommunicationapi.exceptions.ObjectNotFoundException;
import br.com.caulox.luizalabscommunicationapi.repository.ScheduleRepository;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePatchRequest;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        checkValidEnum(Type.class, schedulePostRequest.getType().toUpperCase(),
                "Tipo inválido de mensagem! Tipos permitidos: " + Arrays.asList(Type.values()));

        Type typeMessage = Type.valueOf(schedulePostRequest.getType().toUpperCase());

        if (typeMessage.equals(Type.EMAIL)) {
            isValidEmailAddress(schedulePostRequest.getReceiver());
        } else {
            isValidCellphoneNumber(schedulePostRequest.getReceiver());
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

    public void updateStatus(Long id, SchedulePatchRequest schedulePatchRequest) {
        checkValidEnum(Status.class, schedulePatchRequest.getStatus().toUpperCase(),
                "Status inválido! Tipos permitidos: " + Arrays.asList(Status.values()));

        Schedule schedule = findByIdOrThrowBadRequestException(id);
        schedule.setStatus(Status.valueOf(schedulePatchRequest.getStatus().toUpperCase()));
        schedule.setUpdatedAt(LocalDateTime.now());
        scheduleRepository.save(schedule);
    }

    private <T extends Enum<T>> void checkValidEnum(Class<T> enumType, String value, String msg) {
        try {
            Enum.valueOf(enumType, value);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(msg);
        }
    }

    private void isValidEmailAddress(String email) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            throw new IllegalArgumentException(
                    "E-mail de destinatário inválido! Verifique os dados informados e se o tipo de mensagem está correto");
        }
    }

    private void isValidCellphoneNumber(String phone) {
        String expression =
                "^\\(?(?:[14689][1-9]|2[12478]|3[1234578]|5[1345]|7[134579])\\)? ?(?:[2-8]|9[1-9])[0-9]{3}-?[0-9]{4}$";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(phone);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Telefone celular inválido! " +
                    "Formatos válidos: 11911112222, 11 91111-2222, (11)911112222, (11) 91111-2222");
        }
    }
}
