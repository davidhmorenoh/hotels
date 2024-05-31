package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.enums.StatusDto;
import com.management.hotels.domain.entities.enums.Status;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplStatusMapper implements GenericMapper<StatusDto, Status> {

    private final ModelMapper modelMapper;

    @Override
    public StatusDto toDto(Status status) {
        return modelMapper.map(status, StatusDto.class);
    }

    @Override
    public Status toEntity(StatusDto statusDto) {
        return modelMapper.map(statusDto, Status.class);
    }

}