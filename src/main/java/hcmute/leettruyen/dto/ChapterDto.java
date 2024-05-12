package hcmute.leettruyen.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChapterDto {
    @NotBlank(message = "Title is required")
    private String title;
    private int price;
    @NotNull(message = "Index is required")
    private int index;
    private Boolean active;
    @NotNull
    private Integer book;
}
