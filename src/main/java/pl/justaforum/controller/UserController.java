package pl.justaforum.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.justaforum.model.UserRegistrationDto;
import pl.justaforum.persistence.entity.Token;
import pl.justaforum.service.TokenService;
import pl.justaforum.service.UserService;

import javax.servlet.http.HttpServletRequest;
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

        return "auth/users";
    }

    @ModelAttribute("userRegistrationDto")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping("/signup")
    public String signUpForm() {

        return "auth/signup";
    }

    @PostMapping("/signup")
    public ModelAndView signUp(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto , BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("auth/signup");

            return modelAndView;
        }

        userService.addUser(userRegistrationDto);

        modelAndView.addObject("confirmStart", "Activate your email: " + userRegistrationDto.getEmail());
        modelAndView.setViewName("auth/login");

        return modelAndView;
    }

    @GetMapping("/confirm")
    ModelAndView confirmMail(@RequestParam("token") String token) {

        ModelAndView modelAndView = new ModelAndView();

        Optional<Token> optionalToken = tokenService.findToken(token);

        if (optionalToken.isPresent()) {
            userService.confirmUser(optionalToken.get());
            modelAndView.addObject("confirmDone","Email address has been confirmed.");
            modelAndView.setViewName("auth/login");

            return modelAndView;
        }

        modelAndView.addObject("confirmError","The link is invalid or broken.");
        modelAndView.setViewName("auth/login");

        return modelAndView;
    }

    @GetMapping("/login")
    public String login() {

        return "auth/login";
    }

    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);

        return "auth/login";
    }


    @GetMapping("/logout")
    public String logout() {

        return "index";
    }

    @PostMapping("/users/del/{id}")
    public String delUser(@PathVariable Long id) {

        userService.delUserById(id);

        return "redirect:/logout";
    }

}
