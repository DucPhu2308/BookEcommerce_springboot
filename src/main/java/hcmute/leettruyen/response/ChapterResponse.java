package hcmute.leettruyen.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterResponse {
    private int id;
    private String title;
    private int price;
    private int index;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int bookId;
    private boolean bought;
    private int view;
}
