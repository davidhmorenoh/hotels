package com.management.hotels.infrastructure.adapters.mappers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GenericMapper<DTO, Entity> {

    private final ModelMapper modelMapper;
    private final Class<DTO> dtoClass;
    private final Class<Entity> entityClass;

    public DTO toDTO(Entity entity) {
        return modelMapper.map(entity, dtoClass);
    }

    public Entity toEntity(DTO dto) {
        return modelMapper.map(dto, entityClass);
    }

}