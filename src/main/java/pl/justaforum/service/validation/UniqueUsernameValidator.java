package pl.justaforum.service.validation;

import lombok.AllArgsConstructor;
import pl.justaforum.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

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
