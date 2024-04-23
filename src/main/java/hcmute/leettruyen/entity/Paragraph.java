package hcmute.leettruyen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToMany(mappedBy = "bookmarks")
    @JsonBackReference
    private List<User> users_book_mark;
}
