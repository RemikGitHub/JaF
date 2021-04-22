package pl.justaforum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.justaforum.model.CommentDto;
import pl.justaforum.model.NewCommentDto;
import pl.justaforum.persistence.entity.Comment;
import pl.justaforum.persistence.repository.CommentRepository;
import pl.justaforum.utils.CommentConverter;
import pl.justaforum.utils.LoggedUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentDto> getPostComments(Long id) {
        List<Comment> result = commentRepository.findByPostIdOrderByWriteDateTimeDesc(id);
        return result.stream().map(CommentConverter::createCommentDto).collect(Collectors.toList());
    }

    public void addComment(NewCommentDto request) {

        Comment comment = CommentConverter.createComment(request);
        commentRepository.save(comment);
    }

    public void delCommentById(Long id) throws RuntimeException {

        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isEmpty()) throw new RuntimeException("The comment does not exist.");
        if (!optionalComment.get().getUser().getUsername().equals(LoggedUser.getLoggedUsername())) throw new RuntimeException("You are not authorized!");

        commentRepository.delete(optionalComment.get());


    }
}
