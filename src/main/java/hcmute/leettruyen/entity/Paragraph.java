package hcmute.leettruyen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Chapter chapter;
    @ManyToMany
    @JoinTable(
            name = "book_mark",
            joinColumns = @JoinColumn(name = "paragraph_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonBackReference
    private List<User> users;
}
