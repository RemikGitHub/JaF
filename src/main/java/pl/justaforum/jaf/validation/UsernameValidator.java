package pl.justaforum.jaf.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.justaforum.jaf.users.User;
import pl.justaforum.jaf.users.UserService;

@Component
public class UsernameValidator implements Validator {

    UserService userService;

    @Autowired
    public UsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User)target;

        if(userService.usernameExists(user.getUsername())){
            errors.rejectValue( "username","username.exist", "There is already a user with this.");
        }

    }
}
