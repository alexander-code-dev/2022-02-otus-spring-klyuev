package ru.otus.spring.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.spring.events.EventsPublisher;

@ShellComponent
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationEventsCommands {

    EventsPublisher eventsPublisher;

    @ShellMethod(value = "Let's start testing", key = {"s", "start"})
    public void login() {
        eventsPublisher.publish();
    }
}
