package ru.otus.spring.events.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.otus.spring.events.EventsPublisher;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EventsPublisherImpl implements EventsPublisher {

    ApplicationEventPublisher publisher;

    @Override
    public void publish() {
        publisher.publishEvent(new StartTestEvent("Ok, launch test ..."));
    }
}
