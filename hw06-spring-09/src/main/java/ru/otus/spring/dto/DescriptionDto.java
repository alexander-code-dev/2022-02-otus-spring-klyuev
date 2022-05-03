package ru.otus.spring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DescriptionDto {
    Long id;
    String description;
}
