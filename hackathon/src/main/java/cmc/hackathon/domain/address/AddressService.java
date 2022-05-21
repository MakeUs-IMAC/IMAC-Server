package cmc.hackathon.domain.address;

import cmc.hackathon.domain.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Post> search(String keyword) {
        List<Address> addresses = addressRepository.findByNameContains(keyword);
        List<Post> posts = addresses.stream().map(a -> a.getPlace().getPost())
                .collect(Collectors.toList());

        return posts;

    }
}
