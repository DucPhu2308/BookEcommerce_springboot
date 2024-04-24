package hcmute.leettruyen.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterResponse {
    private String title;
    private int price;
    private int index;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
