package hcmute.leettruyen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Genre {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns =@JoinColumn(name = "genre_id"),
            inverseJoinColumns =@JoinColumn(name = "book_id")
    )
    private List<Book> books;
}
