package pl.justaforum.jaf.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/index")
    public String getUsers(Model model){


        List<User> userList = userRepository.findAll();

        model.addAttribute("users",userList);



        return "index";

    }

}
