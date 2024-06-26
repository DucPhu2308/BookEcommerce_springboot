package hcmute.leettruyen.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInfoDto {
    @NotBlank(message = "display is required")
    private String displayName;
    private String introduction;
    private int coin;
    private boolean active;
    private String avatar;
}
