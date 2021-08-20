package br.com.caulox.luizalabscommunicationapi.controller;

import br.com.caulox.luizalabscommunicationapi.domain.Schedule;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePatchRequest;
import br.com.caulox.luizalabscommunicationapi.requests.SchedulePostRequest;
import br.com.caulox.luizalabscommunicationapi.service.ScheduleService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("communication-api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Agendamento criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos parâmetros da requisição"),
    })
    @PostMapping
    public ResponseEntity<Schedule> save(@RequestBody @Valid SchedulePostRequest schedulePostRequest) {
        return new ResponseEntity<>(this.scheduleService.save(schedulePostRequest), HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Consulta de agendamento com sucesso"),
            @ApiResponse(responseCode = "400", description = "Tipo de id inválido"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @GetMapping("{id}")
    public ResponseEntity<Schedule> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.scheduleService.findByIdOrThrowObjectNotFoundException(id));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Operação com Sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro nos parâmetros da requisição"),
            @ApiResponse(responseCode = "404", description = "Agendamento não encontrado")
    })
    @PatchMapping("{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id,
                                             @RequestBody SchedulePatchRequest schedulePatchRequest) {
        this.scheduleService.updateStatus(id, schedulePatchRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
