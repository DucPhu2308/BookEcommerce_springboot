package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.UserDto;
import hcmute.leettruyen.entity.User;

public interface IUserService {
    User createUser(UserDto userDto);
    String login(String email, String passWord) throws Exception;
    User findByEmail(String email);
    Boolean existsByEmail(String email);
}
