package pl.justaforum.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.justaforum.persistence.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdOrderByWriteDateTimeDesc(Long id);
    Optional<Comment> findById(Long id);
}
