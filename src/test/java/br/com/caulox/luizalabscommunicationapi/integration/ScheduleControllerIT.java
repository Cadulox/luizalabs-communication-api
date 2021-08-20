package br.com.caulox.luizalabscommunicationapi.integration;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import br.com.caulox.luizalabscommunicationapi.repository.ScheduleRepository;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePostRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static br.com.caulox.luizalabscommunicationapi.util.ScheduleCreator.createScheduleToBeSaved;
import static br.com.caulox.luizalabscommunicationapi.util.SchedulePatchRequestCreator.createSchedulePatchRequest;
import static br.com.caulox.luizalabscommunicationapi.util.SchedulePostRequestCreator.createSchedulePostRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class ScheduleControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("findById returns schedule when Successful")
    void findById_ReturnSchedule_WhenSuccessful() {
        Schedule savedSchedule = scheduleRepository.save(createScheduleToBeSaved());
        Long expectedId = savedSchedule.getId();

        Schedule schedule = testRestTemplate
                .getForObject("/communication-api/schedules/{id}", Schedule.class, expectedId);

        Assertions.assertThat(schedule).isNotNull();
        Assertions.assertThat(schedule.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("save returns schedule when Successful")
    void save_ReturnSchedule_WhenSuccessful() {
        SchedulePostRequest schedulePostRequest = createSchedulePostRequest();
        ResponseEntity<Schedule> scheduleResponseEntity = testRestTemplate
                .postForEntity("/communication-api/schedules", schedulePostRequest, Schedule.class);

        Assertions.assertThat(scheduleResponseEntity).isNotNull();
        Assertions.assertThat(scheduleResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(scheduleResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(scheduleResponseEntity.getBody().getId()).isNotNull();
    }

    @Test
    @DisplayName("updateStatus updates schedule when Successful")
    void updateStatus_UpdatesSchedule_WhenSuccessful() throws JsonProcessingException {
        Schedule savedSchedule = scheduleRepository.save(createScheduleToBeSaved());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(createSchedulePatchRequest());

        RestTemplate restTemplate = new RestTemplate();
        HttpClient httpClient = HttpClientBuilder.create().build();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Void> scheduleResponseEntity = testRestTemplate
                .exchange("/communication-api/schedules/{id}", HttpMethod.PATCH,
                        new HttpEntity<>(jsonStr, httpHeaders), Void.class, savedSchedule.getId());

        Assertions.assertThat(scheduleResponseEntity).isNotNull();
        Assertions.assertThat(scheduleResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
