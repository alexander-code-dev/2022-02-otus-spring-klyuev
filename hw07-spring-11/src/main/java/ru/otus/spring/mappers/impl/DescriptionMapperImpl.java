package ru.otus.spring.mappers.impl;

import org.springframework.stereotype.Component;
import ru.otus.spring.dto.DescriptionDto;
import ru.otus.spring.mappers.DescriptionMapper;

@Component
public class DescriptionMapperImpl implements DescriptionMapper {
    public DescriptionDto convertToDto(String description) {
        return DescriptionDto.builder().description(description).build();
    }
}
