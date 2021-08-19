package br.com.caulox.luizalabscommunicationapi.util;

import br.com.caulox.luizalabscommunicationapi.requests.SchedulePostRequest;

import static br.com.caulox.luizalabscommunicationapi.util.ScheduleCreator.createScheduleToBeSaved;

public class SchedulePostRequestCreator {

    public static SchedulePostRequest createSchedulePostRequest() {
        return SchedulePostRequest.builder()
                .requester(createScheduleToBeSaved().getRequester())
                .sendDateTime("18/09/2021 21:00")
                .type(createScheduleToBeSaved().getType().toString())
                .receiver(createScheduleToBeSaved().getReceiver())
                .message(createScheduleToBeSaved().getMessage())
                .build();
    }
}
