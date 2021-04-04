package pl.justaforum.model;

import lombok.*;
import pl.justaforum.persistence.entity.PostCategory;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private Long id;
    private String title;
    private String content;
    private PostCategory postCategory;
    private String publishedDateTime;
    private String username;
}
