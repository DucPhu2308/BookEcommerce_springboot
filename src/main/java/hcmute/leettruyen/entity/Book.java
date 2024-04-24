package hcmute.leettruyen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Book extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private Float avgRating;
    private String coverImage;
    private LocalDateTime publicDate;
    private Boolean active;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_genre",
            joinColumns =@JoinColumn(name = " book_id"),
            inverseJoinColumns =@JoinColumn(name = "genre_id")
    )
    @JsonManagedReference
    private List<Genre> genres;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_author",
            joinColumns =@JoinColumn(name = "book_id"),
            inverseJoinColumns =@JoinColumn(name = "author_id")
    )
    @JsonManagedReference
    private List<Author> authors;
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Rating> ratings;
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<Chapter> chapters;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "follow",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonBackReference
    private List<User> users_follow;
    @ManyToOne
    @JoinColumn(
            name = "user_own_id"
    )
    @JsonBackReference
    private User userOwn;
}
