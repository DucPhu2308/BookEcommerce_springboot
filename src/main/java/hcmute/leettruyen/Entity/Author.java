package hcmute.leettruyen.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String avatarLink;
    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns =@JoinColumn(name = "author_id"),
            inverseJoinColumns =@JoinColumn(name = "book_id")
    )
    private List<Book> books;
}
