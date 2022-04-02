package ru.otus.spring.service.Impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.service.MessageService;

import java.util.Locale;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageServiceImpl implements MessageService {

    MessageSource messageSource;

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, null,  Locale.getDefault());
    }

    @Override
    public String getMessage(String code, Object[] arg) {
        return messageSource.getMessage(code, arg, Locale.getDefault());
    }
}
