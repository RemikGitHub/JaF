package pl.justaforum.model;

import lombok.*;
import pl.justaforum.persistence.entity.PostCategory;
import pl.justaforum.persistence.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewPostDto {

    @Size(min = 3, max = 50, message = "The title must contain between 3 and 50 characters.")
    private String title;

    @Size(min = 3, max = 10000, message = "The title must contain between 3 and 10000 characters.")
    private String content;

    @NotNull
    private PostCategory postCategory;

    private LocalDateTime publishedDateTime;
    private User user;
}
