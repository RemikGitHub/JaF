package pl.justaforum.utils;

import pl.justaforum.model.CommentDto;
import pl.justaforum.model.NewCommentDto;
import pl.justaforum.model.NewPostDto;
import pl.justaforum.model.PostDto;
import pl.justaforum.persistence.entity.Comment;
import pl.justaforum.persistence.entity.Post;

import java.time.format.DateTimeFormatter;

public class CommentConverter {

    private CommentConverter() {
    }

    public static Comment createComment(NewCommentDto source) {
        return Comment.builder()
                .content(source.getContent())
                .writeDateTime(source.getWriteDateTime())
                .post(source.getPost())
                .user(source.getUser())
                .build();
    }

    public static CommentDto createCommentDto(Comment source) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

        return CommentDto.builder()
                .id(source.getId())
                .content(source.getContent())
                .writeDateTime(source.getWriteDateTime().format(formatter))
                .username(source.getUser().getUsername())
                .build();
    }
}
