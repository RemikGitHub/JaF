package pl.justaforum.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.justaforum.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueUsername uniqueUsername) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {

        if (!userService.usernameExists(username)) return true;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("There is already a user with this username.")
                .addConstraintViolation();
        return false;
    }
}
