package hcmute.leettruyen.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
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
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    private String email;
    private String displayName;
    private String password;
    private int coin;
    private String introduction;
    private boolean active;
    private String avatar;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<UserTransaction> userTransactionList;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Rating> ratings;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "mark",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "paragraph_id")
    )
    private List<Paragraph> bookmarks;
    @ManyToMany(mappedBy = "users_follow")
    private List<Book> books;
    @OneToMany(mappedBy = "userOwn")
    @JsonManagedReference
    private List<Book> own;
    @ManyToMany(mappedBy = "subscribing")
    private List<User> subscribed;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_subscribe",
            joinColumns = @JoinColumn(name = "subscriber_id"),
            inverseJoinColumns = @JoinColumn(name = "subscribed_id")
    )
    private List<User> subscribing;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (Role role : roles) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        return authorityList;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> getAllRoles(){
        List<String> roles = new ArrayList<>();
        for (Role role : this.roles) {
            roles.add(role.getName());
        }
        return roles;
    }
}
