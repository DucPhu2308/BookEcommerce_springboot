package hcmute.leettruyen.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDto {
    @NotBlank(message = "Genre name cannot be blank!!!")
    private String name;
    @NotBlank(message = "Select color for genre!")
    private String color;
}
