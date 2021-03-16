package pl.justaforum.jaf.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface UsernameConstraint {

    String message() default "Invalid Username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}