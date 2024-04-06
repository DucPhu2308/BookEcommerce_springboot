package hcmute.leettruyen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChapterDto {
    private String title;
    private int price;
    private int index;
    private Integer book;
}
