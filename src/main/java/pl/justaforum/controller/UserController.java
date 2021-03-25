package pl.justaforum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pl.justaforum.model.UserRegistrationDto;
import pl.justaforum.persistence.entity.Token;
import pl.justaforum.service.TokenService;
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

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/signup")
    public String signUpForm() {

        return "signup";
    }

    @PostMapping("/signup")
    public ModelAndView signUp(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto , BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("signup");

            return modelAndView;
        }

        userService.addUser(userRegistrationDto);

        modelAndView.addObject("confirmStart", "Activate your email: " + userRegistrationDto.getEmail());
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @GetMapping("/confirm")
    ModelAndView confirmMail(@RequestParam("token") String token) {

        ModelAndView modelAndView = new ModelAndView();

        Optional<Token> optionalToken = tokenService.findToken(token);

        if (optionalToken.isPresent()) {
            userService.confirmUser(optionalToken.get());
            modelAndView.addObject("confirmDone","Email address has been confirmed.");
            modelAndView.setViewName("login");

            return modelAndView;
        }

        modelAndView.addObject("confirmError","The link is invalid or broken.");
        modelAndView.setViewName("login");

        return modelAndView;
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);

        return "login";
    }


    @GetMapping("/logout")
    public String logout() {

        return "index";
    }

}
