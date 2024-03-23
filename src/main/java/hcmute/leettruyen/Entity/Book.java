package hcmute.leettruyen.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
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
}
