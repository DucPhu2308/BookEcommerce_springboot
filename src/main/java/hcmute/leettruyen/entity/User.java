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
}
