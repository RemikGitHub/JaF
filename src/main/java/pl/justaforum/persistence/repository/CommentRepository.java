package pl.justaforum.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.justaforum.persistence.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostIdOrderByWriteDateTimeDesc(Long id);
}
