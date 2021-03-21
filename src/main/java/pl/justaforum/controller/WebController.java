package pl.justaforum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {

        return "index";
    }

    @GetMapping("/my-posts")
    public String myPosts(Principal principal, Model model) {

        model.addAttribute("name", principal.getName());
        return "myposts";
    }

}
