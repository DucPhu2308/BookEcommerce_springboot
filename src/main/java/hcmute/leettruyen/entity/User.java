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
@Table(
        name = "t_user"
)
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String username;
    private String password;
    private Integer coin;
    private String introduction;
    private Boolean active;
    @OneToMany(mappedBy = "user")
    private List<UserTransaction> userTransactionList;
    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
    @OneToMany(mappedBy = "user")
    private List<PurchasedHistory> purchasedHistories;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @ManyToMany(mappedBy = "users")
    private List<Paragraph> paragraphs;
    @ManyToMany(mappedBy = "users_follow")
    private List<Book> books;
    @OneToMany(mappedBy = "user_own")
    private List<Book> own;
    @ManyToMany(mappedBy = "subscribing")
    private List<User> subscribed;
    @ManyToMany
    @JoinTable(
            name = "user_subscribe",
            joinColumns = @JoinColumn(name = "subscribing_id"),
            inverseJoinColumns = @JoinColumn(name = "subscribed_id")
    )
    private List<User> subscribing;
}
