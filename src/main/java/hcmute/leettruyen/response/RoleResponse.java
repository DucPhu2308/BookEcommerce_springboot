package hcmute.leettruyen.response;

import hcmute.leettruyen.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponse {
    private String name;
    private List<UserResponse> users;
}
