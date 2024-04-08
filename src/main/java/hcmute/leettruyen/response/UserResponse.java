package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.*;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String email;
    private String username;
    private int coin;
    private String introduction;
    private boolean active;
    private List<UserTransaction> userTransactionList;
    private List<Rating> ratings;
    private List<Role> roles;
    private List<PurchasedHistory> purchasedHistories;
    private List<Comment> comments;
    private List<Paragraph> paragraphs;
    private List<Book> books;
    private List<Book> own;
    private List<User> subscribed;
    private List<User> subscribing;
}
