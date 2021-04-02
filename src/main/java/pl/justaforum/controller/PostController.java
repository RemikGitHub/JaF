package pl.justaforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping("/my-posts")
    public String myPosts() {

        return "myposts/myposts";
    }

    @GetMapping("/my-posts/new-post")
    public String addNewPost() {

        return "myposts/newpost";
    }

    @GetMapping("/backend")
    public String backend() {

        return "posts/backend";
    }

    @GetMapping("/frontend")
    public String frontend() {

        return "posts/frontend";
    }

    @GetMapping("/mobile")
    public String mobile() {

        return "posts/mobile";
    }
}
