package hcmute.leettruyen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment parent;
    @OneToMany(mappedBy = "parent")
    private List<Comment> children;
    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
