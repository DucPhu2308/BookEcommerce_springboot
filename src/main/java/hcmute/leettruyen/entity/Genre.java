package hcmute.leettruyen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Genre {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String color;
    @ManyToMany(mappedBy = "genres")
    @JsonBackReference
    private List<Book> books;
}
