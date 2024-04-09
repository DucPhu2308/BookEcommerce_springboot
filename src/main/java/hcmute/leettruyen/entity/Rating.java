package hcmute.leettruyen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Rating extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer id;
    private Float star;
    private String content;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
