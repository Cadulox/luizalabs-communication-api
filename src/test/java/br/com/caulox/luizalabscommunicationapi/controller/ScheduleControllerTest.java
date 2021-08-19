package br.com.caulox.luizalabscommunicationapi.controller;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePatchRequest;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePostRequest;
import br.com.caulox.luizalabscommunicationapi.service.ScheduleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.caulox.luizalabscommunicationapi.util.ScheduleCreator.createValidSchedule;
import static br.com.caulox.luizalabscommunicationapi.util.SchedulePatchRequestCreator.createSchedulePatchRequest;
import static br.com.caulox.luizalabscommunicationapi.util.SchedulePostRequestCreator.createSchedulePostRequest;

@ExtendWith(SpringExtension.class)
class ScheduleControllerTest {

    @InjectMocks
    private ScheduleController scheduleController;

    @Mock
    private ScheduleService scheduleServiceMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(scheduleServiceMock.findByIdOrThrowObjectNotFoundException(ArgumentMatchers.anyLong()))
                .thenReturn(createValidSchedule());

        BDDMockito.when(scheduleServiceMock.save(ArgumentMatchers.any(SchedulePostRequest.class)))
                .thenReturn(createValidSchedule());

        BDDMockito.doNothing()
                .when(scheduleServiceMock)
                .updateStatus(ArgumentMatchers.anyLong(), ArgumentMatchers.any(SchedulePatchRequest.class));
    }

    @Test
    @DisplayName("findById returns schedule when Successful")
    void findById_ReturnSchedule_WhenSuccessful() {
        Long expectedId = createValidSchedule().getId();

        Schedule schedule = scheduleController.findById(1L).getBody();

        Assertions.assertThat(schedule).isNotNull();
        Assertions.assertThat(schedule.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("save returns schedule when Successful")
    void save_ReturnSchedule_WhenSuccessful() {
        Schedule schedule = scheduleController.save(createSchedulePostRequest()).getBody();

        Assertions.assertThat(schedule).isNotNull().isEqualTo(createValidSchedule());
    }

    @Test
    @DisplayName("updateStatus updates schedule when Successful")
    void updateStatus_UpdatesSchedule_WhenSuccessful() {
        ResponseEntity<Void> entity = scheduleController
                .updateStatus(createValidSchedule().getId(), createSchedulePatchRequest());

        Assertions.assertThat(entity).isNotNull();
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        Assertions.assertThatCode(() -> scheduleController
                .updateStatus(createValidSchedule().getId(), createSchedulePatchRequest()))
                .doesNotThrowAnyException();
    }
}