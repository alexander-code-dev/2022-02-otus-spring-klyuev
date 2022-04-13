package ru.otus.spring.events.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.TestService;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class StartTestEventListenerImpl {

    TestService tService;

    @EventListener
    public void listenEvent(StartTestEvent event) {
        System.out.println(event.getSource());
        tService.runTest();
    }
}
