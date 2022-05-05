package ru.otus.spring.mappers;

import ru.otus.spring.dto.DescriptionDto;

public interface DescriptionMapper {
    DescriptionDto convertToDto(String description);
}
