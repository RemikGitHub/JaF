package pl.justaforum.service.validation;

import pl.justaforum.model.UserChangePasswordDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NewPasswordMatchesValidator implements ConstraintValidator<NewPasswordMatches, Object> {

    @Override
    public void initialize(final NewPasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Object obj, final ConstraintValidatorContext context) {
        final UserChangePasswordDto user = (UserChangePasswordDto) obj;

        if (user.getOldPassword().equals(user.getNewPassword())){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("You have entered the old password.")
                    .addConstraintViolation();
            return false;
        }

        return user.getNewPassword().equals(user.getConfirmNewPassword());
    }

}
