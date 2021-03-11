package pl.justaforum.jaf.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers(Model model){

        model.addAttribute("users",userService.getUsersList());

        return "users";
    }

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/signup")
    public String signUp(Model model){

        model.addAttribute("user", new User());

        return "signup";
    }

    @PostMapping("/register")
    public String signUp(User user){
        userService.addUser(user);
        return "signup";
    }

}
