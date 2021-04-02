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

    @GetMapping("/about-us")
    public String aboutUs() {

        return "about";
    }

}
