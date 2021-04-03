package pl.justaforum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.justaforum.model.NewPostDto;
import pl.justaforum.model.PostDto;
import pl.justaforum.persistence.entity.Post;
import pl.justaforum.persistence.entity.PostCategory;
import pl.justaforum.persistence.repository.PostRepository;
import pl.justaforum.utils.LoggedUser;
import pl.justaforum.utils.PostConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto> getLoggedUserPosts() {
        List<Post> result = postRepository.findByUserId_UsernameOrderByPublishedDateTimeDesc(LoggedUser.getLoggedUsername());

        return result.stream().map(PostConverter::createPostDto).collect(Collectors.toList());
    }

    public List<PostDto> findByCategory(PostCategory postCategory) {
        List<Post> result = postRepository.findByPostCategoryOrderByPublishedDateTimeDesc(postCategory);
        return result.stream().map(PostConverter::createPostDto).collect(Collectors.toList());
    }

    public void addPost(NewPostDto request) {

        Post post = PostConverter.createPost(request);
        postRepository.save(post);
    }
}
