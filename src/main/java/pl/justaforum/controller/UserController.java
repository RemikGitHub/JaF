package pl.justaforum.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.justaforum.persistence.entity.Token;
import pl.justaforum.service.TokenService;
import pl.justaforum.persistence.entity.User;
import pl.justaforum.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final TokenService tokenService;


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
    public String signUp(@Valid User user , BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/signup";
        }

        userService.addUser(user);

        return "redirect:/login";
    }

    @GetMapping("/signup/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<Token> optionalToken = tokenService.findToken(token);

        optionalToken.ifPresent(userService::confirmUser);

        return "/login";
    }


    @GetMapping("/logout")
    public String logout() {

        return "index";
    }

}
