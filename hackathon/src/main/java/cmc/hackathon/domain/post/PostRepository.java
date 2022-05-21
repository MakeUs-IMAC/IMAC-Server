package cmc.hackathon.domain.post;

import cmc.hackathon.domain.member.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByDriverFlag(int flag);
}
