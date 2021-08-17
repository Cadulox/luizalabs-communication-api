package br.com.caulox.luizalabscommunicationapi.repository;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
