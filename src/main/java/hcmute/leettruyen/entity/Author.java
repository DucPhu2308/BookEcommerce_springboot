package hcmute.leettruyen.entity;

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
    private List<Book> books;
}
