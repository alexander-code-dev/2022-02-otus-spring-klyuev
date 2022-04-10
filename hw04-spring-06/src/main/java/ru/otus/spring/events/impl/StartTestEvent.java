package ru.otus.spring.events.impl;

import org.springframework.context.ApplicationEvent;

public class StartTestEvent extends ApplicationEvent {

    public StartTestEvent(Object source) {
        super(source);
    }
}
