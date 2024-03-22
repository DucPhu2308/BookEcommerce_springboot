package hcmute.leettruyen.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class book {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String description;
    private Float avgRating;
    private String coverImage;
    @ManyToMany(mappedBy = "books")
    private List<genre> genres;
}
