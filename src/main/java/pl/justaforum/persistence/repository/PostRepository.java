package pl.justaforum.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.justaforum.persistence.entity.Post;
import pl.justaforum.persistence.entity.PostCategory;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByPostCategory(PostCategory postCategory);
    List<Post> findByUserId_Username(String username);
}
