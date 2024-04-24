package hcmute.leettruyen.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponse {
    private int id;
    private Float star;
    private String content;
}
