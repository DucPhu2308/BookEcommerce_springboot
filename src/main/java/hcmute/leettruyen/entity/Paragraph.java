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
public class Paragraph {
    @Id
    @GeneratedValue
    private Integer id;
    private String content;
    private int index;
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
    @ManyToMany
    @JoinTable(
            name = "book_mark",
            joinColumns = @JoinColumn(name = "paragraph_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}
