package hcmute.leettruyen.constraint;

import hcmute.leettruyen.service.IUserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private final IUserService userService;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value != null && !userService.existsByEmail(value);
    }
}