package hcmute.leettruyen.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponse {
    private int id;
    private Float star;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserResponse user;
}
