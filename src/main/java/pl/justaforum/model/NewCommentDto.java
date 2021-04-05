package pl.justaforum.model;

import lombok.*;
import pl.justaforum.persistence.entity.Post;
import pl.justaforum.persistence.entity.User;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCommentDto {

    private String content;
    private LocalDateTime writeDateTime;
    private Post post;
    private User user;

}
