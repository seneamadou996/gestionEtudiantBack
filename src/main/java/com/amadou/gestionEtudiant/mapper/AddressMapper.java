package com.amadou.gestionEtudiant.mapper;

import com.amadou.gestionEtudiant.dto.AddressDto;
import com.amadou.gestionEtudiant.model.Address;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressMapper {

    private final ModelMapper modelMapper;

    public AddressDto fromEntity(Address address) {
        if (address == null) {
            return null;
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        AddressDto addressDto;
        addressDto = modelMapper.map(address, AddressDto.class);
        return addressDto;

    }

    public Address toEntity(AddressDto addressDto) {
        if (addressDto == null) {
            return null;
        }

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Address address;
        address = modelMapper.map(addressDto, Address.class);
        return address;

    }



}
