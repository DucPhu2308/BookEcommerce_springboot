package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private String title;
    private String description;
    private Float avgRating;
    private String coverImage;
    private LocalDateTime publicDate;
    private List<Genre> genres;
    private List<Author> authors;
    private List<Rating> ratings;
    private List<Chapter> chapters;
    private List<User> users_follow;
    private User user_own;
}
