package hcmute.leettruyen.dto;

import hcmute.leettruyen.constraint.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String password;
}
