package pl.justaforum.utils;

import pl.justaforum.model.NewPostDto;
import pl.justaforum.model.PostDto;
import pl.justaforum.persistence.entity.Post;

import java.time.format.DateTimeFormatter;

public class PostConverter {

    private PostConverter() {
    }

    public static Post createPost(NewPostDto source) {
        return Post.builder()
                .title(source.getTitle())
                .content(source.getContent())
                .postCategory(source.getPostCategory())
                .publishedDateTime(source.getPublishedDateTime())
                .user(source.getUser())
                .build();
    }

    public static PostDto createPostDto(Post source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        return PostDto.builder()
                .title(source.getTitle())
                .content(source.getContent())
                .postCategory(source.getPostCategory())
                .publishedDateTime(source.getPublishedDateTime().format(formatter))
                .username(source.getUser().getUsername())
                .build();
    }
}
