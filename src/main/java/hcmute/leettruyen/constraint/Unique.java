package hcmute.leettruyen.constraint;

import jakarta.validation.Constraint;

import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface Unique {
    String message() default "Field must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}