package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchasedHistoryResponse {
    private int coin;
    private LocalDateTime dateTime;
    private ChapterResponse chapter;
    private UserResponse user;
}
