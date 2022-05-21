package cmc.hackathon.domain.post.dto;

import cmc.hackathon.domain.place.Place;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static java.util.stream.Collectors.*;

@Getter
@Setter
public class PlaceDto {
    private Long placeId;
    private List<AddressDto> addressDtos;

    public PlaceDto(Place place) {
        this.placeId = place.getId();
        this.addressDtos = place.getAddresses().stream()
                .map(AddressDto::new)
                .collect(toList());
    }
}
