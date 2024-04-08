package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParagraphResponse {
    private String content;
    private int index;
    private ChapterResponse chapter;
    private List<UserResponse> users;
}
