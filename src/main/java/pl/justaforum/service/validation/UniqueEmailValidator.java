package pl.justaforum.service.validation;

import lombok.AllArgsConstructor;
import pl.justaforum.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class UniqueEmailValidator  implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

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
