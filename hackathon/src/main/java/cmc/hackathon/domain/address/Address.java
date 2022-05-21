package cmc.hackathon.domain.address;

import cmc.hackathon.domain.place.Place;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Where(clause = "status='ACTIVE'")
@Getter
@Entity
public class Address {

    @Id
    @GeneratedValue
    @Column(name = "address_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 50)
    private String name;


    public static Address create(Place place, String address, String name) {
        Address address1 = new Address();
        address1.changePlace(place);
        address1.createAddress(address, name);
        return address1;

    }

    private void createAddress(String address, String name) {
        this.address = address;
        this.name = name;
    }

    private void changePlace(Place place) {
        this.place = place;
        place.addAddress(this);
    }
}
