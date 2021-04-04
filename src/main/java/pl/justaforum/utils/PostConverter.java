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

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

        return PostDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .content(source.getContent())
                .postCategory(source.getPostCategory())
                .publishedDateTime(source.getPublishedDateTime().format(formatter))
                .username(source.getUser().getUsername())
                .build();
    }

    public static PostDto createShortPostDto(Post source) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");

        int charsInContent = 350;

        String shortContent = source.getContent();
        if (shortContent.length() > charsInContent) {
            shortContent = shortContent.substring(0,charsInContent) + "...";
        }

        return PostDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .content(shortContent)
                .postCategory(source.getPostCategory())
                .publishedDateTime(source.getPublishedDateTime().format(formatter))
                .username(source.getUser().getUsername())
                .build();
    }
}
