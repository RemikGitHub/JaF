package pl.justaforum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.justaforum.model.NewCommentDto;
import pl.justaforum.model.NewPostDto;
import pl.justaforum.persistence.entity.PostCategory;
import pl.justaforum.service.CommentService;
import pl.justaforum.service.PostService;
import pl.justaforum.service.UserService;
import pl.justaforum.utils.LoggedUser;

import javax.validation.Valid;
import java.time.LocalDateTime;

@AllArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @GetMapping("/my-posts")
    public String myPosts(Model model) {

        model.addAttribute("myPosts", postService.getLoggedUserPosts());
        model.addAttribute("numberOfPosts",postService.getLoggedNumberPost());

        return "myposts/myposts";
    }

    @ModelAttribute("newPostDto")
    public NewPostDto newPostDto() {
        return new NewPostDto();
    }

    @GetMapping("/my-posts/new-post")
    public String addNewPostForm() {

        return "myposts/newpost";
    }

    @PostMapping("/my-posts/new-post")
    public ModelAndView addNewPost(@Valid @ModelAttribute("newPostDto") NewPostDto newPostDto , BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("myposts/newpost");

            return modelAndView;
        }

        newPostDto.setPublishedDateTime(LocalDateTime.now());

        String loggedUsername = LoggedUser.getLoggedUsername();
        newPostDto.setUser(userService.getUserEntityByUsername(loggedUsername));

        postService.addPost(newPostDto);
        modelAndView.addObject("confirm", "You've added a post!");
        modelAndView.setViewName("myposts/newpost");

        return modelAndView;
    }

    @GetMapping("/backend")
    public String backend(Model model) {

        model.addAttribute("backendPosts", postService.findByCategory(PostCategory.BACKEND));

        return "posts/backend";
    }

    @GetMapping("/frontend")
    public String frontend(Model model) {

        model.addAttribute("frontendPosts", postService.findByCategory(PostCategory.FRONTEND));

        return "posts/frontend";
    }

    @GetMapping("/mobile")
    public String mobile(Model model) {

        model.addAttribute("mobilePosts", postService.findByCategory(PostCategory.MOBILE));

        return "posts/mobile";
    }

    @GetMapping("/single-post/{id}")
    public String getSinglePost(@PathVariable Long id, Model model) {

        try{
            model.addAttribute("post", postService.getPostById(id));
            model.addAttribute("comments", commentService.getPostComments(id));
        }
        catch (RuntimeException e){
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "posts/singlepost";
    }

    @ModelAttribute("newCommentDto")
    public NewCommentDto newCommentDto() {
        return new NewCommentDto();
    }

    @PostMapping("/single-post/{id}")
    public String addComment(@PathVariable Long id, @ModelAttribute("newCommentDto") NewCommentDto newCommentDto) {

        newCommentDto.setWriteDateTime(LocalDateTime.now());

        String loggedUsername = LoggedUser.getLoggedUsername();
        newCommentDto.setUser(userService.getUserEntityByUsername(loggedUsername));

        newCommentDto.setPost(postService.getPostEntityById(id));

        commentService.addComment(newCommentDto);

        return "redirect:/single-post/"+id;
    }

}
