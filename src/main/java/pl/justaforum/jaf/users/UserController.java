package pl.justaforum.jaf.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.justaforum.jaf.token.Token;
import pl.justaforum.jaf.token.TokenService;

import java.util.Optional;


@Controller
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {

        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getUsersList());

        return "users";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/signup")
    public String signUpForm(User user) {

        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(User user) {

        userService.addUser(user);

        return "redirect:/login";
    }

    @GetMapping("/signup/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<Token> optionalToken = tokenService.findToken(token);

        optionalToken.ifPresent(userService::confirmUser);

        return "/login";
    }


}
