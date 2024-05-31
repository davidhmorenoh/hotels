package com.management.hotels.domain.ports.mappers;

public interface GenericMapper<RequestDto, ResponseDto, Entity> {

    ResponseDto toDto(Entity entity);

    Entity toEntity(RequestDto requestDto);

}