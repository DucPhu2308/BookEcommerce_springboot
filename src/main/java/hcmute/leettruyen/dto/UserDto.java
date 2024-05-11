package hcmute.leettruyen.dto;

import hcmute.leettruyen.constraint.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Email(message = "Email is invalid")
    @NotBlank(message = "Email is required")
    @Unique(message = "Email is already taken")
    private String email;
    private String displayName;
    @NotBlank(message = "Password is required")
    @Length(min = 6, message = "Password must be at least 6 characters")
    private String password;
}
