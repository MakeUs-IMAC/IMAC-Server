package cmc.hackathon.domain.place.dto;

import cmc.hackathon.domain.address.dto.AddressResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceResponseDto {
    private Long id;
    List<AddressResponseDto> addressResponseDtos;
    private String name;
}
