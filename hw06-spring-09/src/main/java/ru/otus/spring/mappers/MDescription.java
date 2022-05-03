package ru.otus.spring.mappers;

import ru.otus.spring.dto.DescriptionDto;

public interface MDescription {
    DescriptionDto convertToDto(String description);
}
