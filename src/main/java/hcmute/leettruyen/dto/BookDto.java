package hcmute.leettruyen.dto;

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
    private String title;
    private String description;
    private String coverImage;
    private List<Integer> genresId;
    private List<Integer> authorsId;
}
