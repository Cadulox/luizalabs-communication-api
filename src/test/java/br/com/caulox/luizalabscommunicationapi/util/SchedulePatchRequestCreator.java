package br.com.caulox.luizalabscommunicationapi.util;

import br.com.caulox.luizalabscommunicationapi.requests.SchedulePatchRequest;

import static br.com.caulox.luizalabscommunicationapi.util.ScheduleCreator.createValidUpdatedSchedule;

public class SchedulePatchRequestCreator {

    public static SchedulePatchRequest createSchedulePatchRequest() {
        return SchedulePatchRequest.builder()
                .status(createValidUpdatedSchedule().getStatus().toString())
                .build();
    }
}
