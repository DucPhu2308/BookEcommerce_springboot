package hcmute.leettruyen.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChapterResponse {
    private int id;
    private String title;
    private int price;
    private int index;
}
