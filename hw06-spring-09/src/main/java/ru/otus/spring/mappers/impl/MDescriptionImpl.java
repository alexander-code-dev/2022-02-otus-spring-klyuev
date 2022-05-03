package ru.otus.spring.mappers.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dto.DescriptionDto;
import ru.otus.spring.mappers.MDescription;

@Component
public class MDescriptionImpl implements MDescription {
    public DescriptionDto convertToDto(String description) {
        return DescriptionDto.builder().description(description).build();
    }
}
