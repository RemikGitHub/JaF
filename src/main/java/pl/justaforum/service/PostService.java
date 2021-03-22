package pl.justaforum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.justaforum.persistence.entity.Post;
import pl.justaforum.persistence.repository.PostRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPostsList() {
        return postRepository.findAll();
    }
}
