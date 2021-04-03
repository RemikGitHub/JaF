package pl.justaforum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.justaforum.model.NewPostDto;
import pl.justaforum.model.PostDto;
import pl.justaforum.persistence.entity.Post;
import pl.justaforum.persistence.entity.PostCategory;
import pl.justaforum.persistence.entity.User;
import pl.justaforum.persistence.repository.PostRepository;
import pl.justaforum.utils.PostConverter;
import pl.justaforum.utils.UserConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto> findByCategory(PostCategory postCategory) {
        List<Post> result = postRepository.findByPostCategory(postCategory);
        return result.stream().map(PostConverter::createPostDto).collect(Collectors.toList());
    }

    public void addPost(NewPostDto request) {

        Post post = PostConverter.createPost(request);
        postRepository.save(post);
    }
}
