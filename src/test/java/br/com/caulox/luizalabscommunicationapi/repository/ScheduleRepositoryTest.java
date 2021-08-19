package br.com.caulox.luizalabscommunicationapi.repository;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import br.com.caulox.luizalabscommunicationapi.domain.enums.Status;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static br.com.caulox.luizalabscommunicationapi.util.ScheduleCreator.createScheduleToBeSaved;

@DataJpaTest
@DisplayName("Tests for Schedule Repository")
class ScheduleRepositoryTest {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Test
    @DisplayName("Save persists schedule when Successful")
    void save_PersistSchedule_WhenSuccessful() {
        Schedule schedule = createScheduleToBeSaved();
        Schedule savedSchedule = this.scheduleRepository.save(schedule);

        Assertions.assertThat(savedSchedule).isNotNull();
        Assertions.assertThat(savedSchedule.getId()).isNotNull();
        Assertions.assertThat(savedSchedule.getStatus()).isEqualTo(schedule.getStatus());
    }

    @Test
    @DisplayName("Save updates schedule when Successful")
    void save_UpdatesSchedule_WhenSuccessful() {
        Schedule schedule = createScheduleToBeSaved();
        Schedule savedSchedule = this.scheduleRepository.save(schedule);

        savedSchedule.setStatus(Status.CANCELED);
        Schedule updatedSchedule = this.scheduleRepository.save(savedSchedule);

        Assertions.assertThat(updatedSchedule).isNotNull();
        Assertions.assertThat(updatedSchedule.getId()).isNotNull();
        Assertions.assertThat(updatedSchedule.getStatus()).isEqualTo(savedSchedule.getStatus());
    }

    @Test
    @DisplayName("Find By Id returns schedule when Successful")
    void findById_ReturnsSchedule_WhenSuccessful() {
        Schedule schedule = createScheduleToBeSaved();
        Schedule savedSchedule = this.scheduleRepository.save(schedule);

        Long id = savedSchedule.getId();
        Optional<Schedule> scheduleOptional = this.scheduleRepository.findById(id);

        Assertions.assertThat(scheduleOptional).isNotEmpty().isNotNull().isEqualTo(Optional.of(savedSchedule));
    }

    @Test
    @DisplayName("Save throw DataIntegrityViolationException when a field is null")
    void save_ThrowsDataIntegrityViolationException_WhenFieldNull() {
        Schedule schedule = new Schedule();
        Assertions.assertThatThrownBy(() -> this.scheduleRepository.save(schedule))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}