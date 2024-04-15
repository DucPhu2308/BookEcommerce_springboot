package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.component.JwtTokenUtil;
import hcmute.leettruyen.dto.UserDto;
import hcmute.leettruyen.entity.Role;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.repository.RoleRepository;
import hcmute.leettruyen.repository.UserRepository;
import hcmute.leettruyen.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    @Override
    public User createUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new DataIntegrityViolationException("Email exist!");
        }
        User user = User.builder()
                .userName(userDto.getUserName())
                .email(userDto.getEmail())
                .build();
        Role role = roleRepository.findById(2)
                .orElseThrow(()-> new RuntimeException("Role not found"));
        user.setRoles(List.of(role));
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public String login(String email, String passWord) throws Exception {
        Optional<User> foundUser = userRepository.findByEmail(email);
        if (foundUser.isEmpty())
            throw new Exception("Invalid email or password");
        User user = foundUser.get();
        if (!passwordEncoder.matches(passWord, user.getPassword()))
            throw new Exception("Invalid email or password");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, passWord, user.getAuthorities()));
        return jwtTokenUtil.generateToken(user);
    }

    @Override
    public User findByEmail(String email) {
        Optional<User> foundUser = userRepository.findByEmail(email);
        return foundUser.orElse(null);
    }
}
