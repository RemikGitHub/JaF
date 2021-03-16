package pl.justaforum.jaf.posts;

import lombok.Getter;
import lombok.Setter;
import pl.justaforum.jaf.users.User;

import javax.persistence.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Column
    private LocalDateTime publishedDateTime;

    @ManyToOne
    User user;
}
