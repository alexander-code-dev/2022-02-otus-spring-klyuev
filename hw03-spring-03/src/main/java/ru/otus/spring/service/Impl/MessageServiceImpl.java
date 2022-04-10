package ru.otus.spring.service.Impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.spring.config.PropertiesForConfig;
import ru.otus.spring.service.MessageService;

import java.util.Locale;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageServiceImpl implements MessageService {

    MessageSource messageSource;
    @NonFinal
    Locale locale;

    public MessageServiceImpl(MessageSource messageSource, PropertiesForConfig properties) {
        this.messageSource = messageSource;
        this.locale = new Locale(properties.getLocale());
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String getMessage(String code) {
        return messageSource.getMessage(code, null,  locale);
    }

    @Override
    public String getMessage(String code, Object[] arg) {
        return messageSource.getMessage(code, arg, locale);
    }
}
