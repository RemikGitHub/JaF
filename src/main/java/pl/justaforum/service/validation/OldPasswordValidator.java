package pl.justaforum.service.validation;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.justaforum.service.UserService;
import pl.justaforum.utils.LoggedUser;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@AllArgsConstructor
public class OldPasswordValidator implements ConstraintValidator<OldPassword, String> {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Override
    public void initialize(OldPassword passwordFromUser) {

    }

    @Override
    public boolean isValid(String passwordFromUser, ConstraintValidatorContext context) {

        String loggedUserPassword = userService.getUserByUsername(LoggedUser.getLoggedUsername()).getPassword();

        if (passwordEncoder.matches(passwordFromUser, loggedUserPassword)) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("You entered the wrong password.")
                .addConstraintViolation();
        return false;
    }
}
