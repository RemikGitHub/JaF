package pl.justaforum.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.justaforum.persistence.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
