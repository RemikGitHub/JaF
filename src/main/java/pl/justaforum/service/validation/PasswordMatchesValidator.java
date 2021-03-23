package pl.justaforum.service.validation;

import pl.justaforum.model.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(final PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserRegistrationDto user = (UserRegistrationDto) obj;
        return user.getPassword().equals(user.getConfirmPassword());
    }

}
