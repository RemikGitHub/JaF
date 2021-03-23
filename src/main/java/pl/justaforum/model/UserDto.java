package pl.justaforum.model;

import lombok.*;
import pl.justaforum.persistence.entity.Post;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;
    private List<Post> posts;
}
