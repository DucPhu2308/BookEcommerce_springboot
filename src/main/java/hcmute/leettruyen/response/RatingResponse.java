package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingResponse {
    private Float star;
    private String content;
    private Book book;
    private User user;
}
