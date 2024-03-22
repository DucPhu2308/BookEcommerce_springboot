package hcmute.leettruyen.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class genre {
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
    private List<book> books;
}
