package hcmute.leettruyen.dto;

import hcmute.leettruyen.constraint.Unique;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "Email is required")
    @Unique(message = "Email is already taken")
    private String email;
    private String userName;
    @NotBlank(message = "Password is required")
    private String password;
}
