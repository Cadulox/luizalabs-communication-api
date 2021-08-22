package br.com.caulox.luizalabscommunicationapi.service;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import br.com.caulox.luizalabscommunicationapi.exceptions.ObjectNotFoundException;
import br.com.caulox.luizalabscommunicationapi.repository.ScheduleRepository;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePatchRequest;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePostRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.caulox.luizalabscommunicationapi.util.ScheduleCreator.createValidSchedule;
import static br.com.caulox.luizalabscommunicationapi.util.SchedulePatchRequestCreator.createSchedulePatchRequest;
import static br.com.caulox.luizalabscommunicationapi.util.SchedulePostRequestCreator.createSchedulePostRequest;

@ExtendWith(SpringExtension.class)
class ScheduleServiceTest {

    @InjectMocks
    private ScheduleService scheduleService;

    @Mock
    private ScheduleRepository scheduleRepositoryMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(scheduleRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(createValidSchedule()));

        BDDMockito.when(scheduleRepositoryMock.save(ArgumentMatchers.any(Schedule.class)))
                .thenReturn(createValidSchedule());
    }

    @Test
    @DisplayName("findByIdOrObjectNotFoundException returns schedule when Successful")
    void findById_ReturnSchedule_WhenSuccessful() {
        Long expectedId = createValidSchedule().getId();

        Schedule schedule = scheduleService.findByIdOrThrowObjectNotFoundException(1L);

        Assertions.assertThat(schedule).isNotNull();
        Assertions.assertThat(schedule.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIdOrThrowObjectNotFoundException thorws ObjectNotFoundException when schedule is not found")
    void findByIdOrThrowObjectNotFoundException_ThrowsObjectNotFoundException_WhenScheduleIsNotFound() {
        BDDMockito.when(scheduleRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> scheduleService.findByIdOrThrowObjectNotFoundException(1L));
    }

    @Test
    @DisplayName("save returns schedule when Successful")
    void save_ReturnSchedule_WhenSuccessful() {
        Schedule schedule = scheduleService.save(createSchedulePostRequest());

        Assertions.assertThat(schedule).isNotNull().isEqualTo(createValidSchedule());
    }

    @Test
    @DisplayName("save throws IllegalArgumentException when sendDateTime is before")
    void save_ThrowsIllegalArgumentException_WhenSendDateTimeBefore() {
        SchedulePostRequest invalidSchedulePostRequest = createSchedulePostRequest();
        invalidSchedulePostRequest.setSendDateTime("18/08/2021 21:00");

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> scheduleService.save(invalidSchedulePostRequest));
    }

    @Test
    @DisplayName("save throws IllegalArgumentException when Type of message is wrong")
    void save_ThrowsIllegalArgumentException_WhenTypeMessageWrong() {
        SchedulePostRequest invalidSchedulePostRequest = createSchedulePostRequest();
        invalidSchedulePostRequest.setType("LETTER");

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> scheduleService.save(invalidSchedulePostRequest));
    }

    @Test
    @DisplayName("save throws IllegalArgumentException when Email is not valid")
    void save_ThrowsIllegalArgumentException_WhenEmailNotValid() {
        SchedulePostRequest invalidSchedulePostRequest = createSchedulePostRequest();
        invalidSchedulePostRequest.setReceiver("ful@no@email.com.br");

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> scheduleService.save(invalidSchedulePostRequest));
    }

    @Test
    @DisplayName("save throws IllegalArgumentException when Cellphone is not valid")
    void save_ThrowsIllegalArgumentException_WhenCellphoneNotValid() {
        SchedulePostRequest invalidSchedulePostRequest = createSchedulePostRequest();
        invalidSchedulePostRequest.setType("SMS");

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> scheduleService.save(invalidSchedulePostRequest));
    }

    @Test
    @DisplayName("updateStatus updates schedule when Successful")
    void updateStatus_UpdatesSchedule_WhenSuccessful() {
        Assertions.assertThatCode(() -> scheduleService
                .updateStatus(createValidSchedule().getId(), createSchedulePatchRequest()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("updateStatus thorws ObjectNotFoundException when schedule is not found")
    void updateStatus_ThrowsObjectNotFoundException_WhenScheduleIsNotFoun() {
        BDDMockito.when(scheduleRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        SchedulePatchRequest schedulePatchRequest = createSchedulePatchRequest();

        Assertions.assertThatExceptionOfType(ObjectNotFoundException.class)
                .isThrownBy(() -> scheduleService.updateStatus(1L, schedulePatchRequest));
    }
}