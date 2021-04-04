package pl.justaforum.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.justaforum.persistence.entity.Post;
import pl.justaforum.persistence.entity.PostCategory;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByPostCategoryOrderByPublishedDateTimeDesc(PostCategory postCategory);
    List<Post> findByUserId_UsernameOrderByPublishedDateTimeDesc(String username);
    Long countByUserId_Username(String username);
    Optional<Post> findById(Long id);
}
