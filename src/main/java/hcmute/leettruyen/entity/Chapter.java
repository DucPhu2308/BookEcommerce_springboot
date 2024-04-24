package hcmute.leettruyen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Chapter extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private int price;
    private int index;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "book_id")
    @JsonBackReference
    private Book book;
    @OneToMany(mappedBy = "chapter")
    @JsonManagedReference
    private List<Comment> comments;
    @OneToMany(mappedBy = "chapter")
    @JsonManagedReference
    private List<PurchasedHistory> purchasedHistories;
    @OneToMany(mappedBy = "chapter")
    @JsonManagedReference
    private List<Paragraph> paragraphs;
}
