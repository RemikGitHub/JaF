package pl.justaforum.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDto {

    private Long id;
    private String content;
    private String writeDateTime;
    private String username;
}
