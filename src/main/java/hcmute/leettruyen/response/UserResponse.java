package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.*;
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
    private List<Rating> ratings;
    private List<Role> roles;
    private List<Comment> comments;
    private List<Paragraph> paragraphs;
    private List<Book> books;
    private List<Book> own;
    private List<User> subscribed;
    private List<User> subscribing;
}
