package br.com.caulox.luizalabscommunicationapi.controller;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePostRequest;
import br.com.caulox.luizalabscommunicationapi.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("communication-api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<Schedule> save(@RequestBody @Valid SchedulePostRequest schedulePostRequest) {
        return new ResponseEntity<>(scheduleService.save(schedulePostRequest), HttpStatus.CREATED);
    }
}
