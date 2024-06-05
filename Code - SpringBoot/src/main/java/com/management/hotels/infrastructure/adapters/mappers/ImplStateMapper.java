package com.management.hotels.infrastructure.adapters.mappers;

import com.management.hotels.application.dtos.enums.StateDto;
import com.management.hotels.domain.entities.enums.State;
import com.management.hotels.domain.ports.mappers.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ImplStateMapper implements GenericMapper<StateDto, StateDto, State> {

    private final ModelMapper modelMapper;

    @Override
    public StateDto toDto(State state) {
        return modelMapper.map(state, StateDto.class);
    }

    @Override
    public State toEntity(StateDto stateDto) {
        return modelMapper.map(stateDto, State.class);
    }

}