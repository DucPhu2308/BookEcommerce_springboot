package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponse {
    private Float star;
    private String content;
    private BookResponse book;
    private UserResponse user;
}
