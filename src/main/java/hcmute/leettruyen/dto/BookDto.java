package hcmute.leettruyen.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    @NotBlank
    private String title;
    private String description;
    private String coverImage;
    private List<Integer> genresDto;
}
