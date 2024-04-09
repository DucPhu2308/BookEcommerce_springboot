package hcmute.leettruyen.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "t_user"
)
@Builder
public class User extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String username;
    private String password;
    private int coin;
    private String introduction;
    private boolean active;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserTransaction> userTransactionList;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Rating> ratings;
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<PurchasedHistory> purchasedHistories;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Comment> comments;
    @ManyToMany(mappedBy = "users")
    private List<Paragraph> paragraphs;
    @ManyToMany(mappedBy = "users_follow")
    private List<Book> books;
    @OneToMany(mappedBy = "userOwn")
    @JsonManagedReference
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
