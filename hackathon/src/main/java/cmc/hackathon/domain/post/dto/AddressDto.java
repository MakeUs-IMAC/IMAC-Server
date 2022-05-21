package cmc.hackathon.domain.post.dto;

import cmc.hackathon.domain.address.Address;
import lombok.Data;

@Data
public class AddressDto {
    private String name;
    private String address;

    public AddressDto(Address address) {
        this.name = address.getName();
        this.address = address.getAddress();
    }
}
