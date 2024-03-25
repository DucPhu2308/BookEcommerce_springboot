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
public class Chapter {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private int price;
    private int index;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @OneToMany(mappedBy = "chapter")
    private List<Comment> comments;
    @OneToMany(mappedBy = "chapter")
    private List<PurchasedHistory> purchasedHistories;
    @OneToMany(mappedBy = "chapter")
    private List<Paragraph> paragraphs;
}
