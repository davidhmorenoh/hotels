package com.management.hotels.domain.ports.mappers;

public interface GenericMapper<Dto, Entity> {

    Dto toDto(Entity entity);

    Entity toEntity(Dto dto);

}