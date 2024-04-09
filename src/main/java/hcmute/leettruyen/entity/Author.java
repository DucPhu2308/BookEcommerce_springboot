package hcmute.leettruyen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Author{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String avatarLink;
    @ManyToMany(mappedBy = "authors")
    @JsonBackReference
    private List<Book> books;
}
