package pl.justaforum.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.justaforum.persistence.entity.Post;
import pl.justaforum.persistence.entity.PostCategory;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByPostCategoryOrderByPublishedDateTimeDesc(PostCategory postCategory);
    List<Post> findByUserId_UsernameOrderByPublishedDateTimeDesc(String username);
}
