package hcmute.leettruyen.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {
    private Integer id;
    private String title;
    private String description;
    private Float avgRating;
    private String coverImage;
    private LocalDateTime publicDate;
    private List<GenreResponse> genres;
    private List<AuthorResponse> authors;
    private List<RatingResponse> ratings;
    private List<ChapterResponse> chapters;
    private List<UserResponse> users_follow;
    private UserResponse userOwn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
