package pl.justaforum.utils;

import pl.justaforum.model.NewPostDto;
import pl.justaforum.model.PostDto;
import pl.justaforum.persistence.entity.Post;

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
        return PostDto.builder()
                .title(source.getTitle())
                .content(source.getContent())
                .postCategory(source.getPostCategory())
                .publishedDateTime(source.getPublishedDateTime())
                .user(source.getUser())
                .build();
    }
}
