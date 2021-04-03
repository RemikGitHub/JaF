package pl.justaforum.model;

import lombok.*;
import pl.justaforum.persistence.entity.PostCategory;
import pl.justaforum.persistence.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {

    private String title;
    private String content;
    private PostCategory postCategory;
    private LocalDateTime publishedDateTime;
    private User user;
}
