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
    @NotBlank
    private String title;
    private int price;
    @NotNull
    private int index;
    @NotNull
    private Integer book;
}
