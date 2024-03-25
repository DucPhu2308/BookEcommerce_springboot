package hcmute.leettruyen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private Float avgRating;
    private String coverImage;
    private Timestamp publicDate;
    @ManyToMany(mappedBy = "books")
    private List<Genre> genres;
    @ManyToMany(mappedBy = "books")
    private List<Author> authors;
    @OneToMany(mappedBy = "book")
    private List<Rating> ratings;
    @OneToMany(mappedBy = "book")
    private List<Chapter> chapters;
    @ManyToMany
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users_follow;
    @ManyToOne
    @JoinColumn(
            name = "user_own_id"
    )
    private User user_own;
}
