package cmc.hackathon.domain.address.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponseDto {
    private Long id;
    private String name;
    private String address;
}
