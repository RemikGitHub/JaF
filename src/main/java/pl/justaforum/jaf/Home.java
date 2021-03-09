package pl.justaforum.jaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @GetMapping("/index")
    public String home(){

        return "index";
    }

    @GetMapping("/myposts")
    public String myPosts(){

        return "myposts";
    }

}
