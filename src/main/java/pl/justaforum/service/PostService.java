package pl.justaforum.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.justaforum.model.NewPostDto;
import pl.justaforum.model.PostDto;
import pl.justaforum.persistence.entity.Post;
import pl.justaforum.persistence.entity.PostCategory;
import pl.justaforum.persistence.repository.PostRepository;
import pl.justaforum.utils.LoggedUser;
import pl.justaforum.utils.PostConverter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto> getLoggedUserPosts() {
        List<Post> result = postRepository.findByUserId_UsernameOrderByPublishedDateTimeDesc(LoggedUser.getLoggedUsername());

        return result.stream().map(PostConverter::createShortPostDto).collect(Collectors.toList());
    }

    public Post getPostEntityById(Long id) throws UsernameNotFoundException {

        return postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found."));
    }

    public Long getLoggedNumberPost() {
        return postRepository.countByUserId_Username(LoggedUser.getLoggedUsername());
    }

    public PostDto getPostById(Long id) throws RuntimeException{
        return PostConverter.createPostDto(postRepository.findById(id).orElseThrow(() -> new RuntimeException("Post not found.")));
    }

    public List<PostDto> findByCategory(PostCategory postCategory) {
        List<Post> result = postRepository.findByPostCategoryOrderByPublishedDateTimeDesc(postCategory);
        return result.stream().map(PostConverter::createShortPostDto).collect(Collectors.toList());
    }

    public void addPost(NewPostDto request) {

        Post post = PostConverter.createPost(request);
        postRepository.save(post);
    }

    public void delPostById(Long id) throws RuntimeException {

        Optional<Post> optionalPost = postRepository.findById(id);

        if (optionalPost.isEmpty()) throw new RuntimeException("The post does not exist.");
        if (!optionalPost.get().getUser().getUsername().equals(LoggedUser.getLoggedUsername())) throw new RuntimeException("You are not authorized!");

        postRepository.deleteById(id);
    }
}
