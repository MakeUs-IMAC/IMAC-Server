package cmc.hackathon.domain.place;

import cmc.hackathon.domain.address.Address;
import cmc.hackathon.domain.post.Post;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Where(clause = "status='ACTIVE'")
@Getter
@Entity
public class Place {

    @Id
    @GeneratedValue
    @Column(name = "place_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public static Place create(Post post) {
        Place place = new Place();
        place.changePost(post);
        return place;
    }

    private void changePost(Post post) {
        this.post = post;
        post.addPlace(this);
    }

    public void addAddress(Address address) {
        addresses.add(address);
    }
}
