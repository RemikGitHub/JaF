package pl.justaforum.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.justaforum.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator  implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    @Override
    public void initialize(UniqueEmail uniqueEmail) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if (!userService.emailExists(email)) return true;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("There is already a user with this email.")
                .addConstraintViolation();
        return false;
    }
}
