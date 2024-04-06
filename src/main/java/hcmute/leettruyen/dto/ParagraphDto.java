package hcmute.leettruyen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParagraphDto {
    private String content;
    private int index;
    private Integer chapter;
}
