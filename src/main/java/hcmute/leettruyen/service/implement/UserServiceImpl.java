package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.component.Extractor;
import hcmute.leettruyen.component.JwtTokenUtil;
import hcmute.leettruyen.dto.UserDto;
import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.Role;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.repository.BookRepository;
import hcmute.leettruyen.repository.RoleRepository;
import hcmute.leettruyen.repository.UserRepository;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookRepository bookRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final ModelMapper modelMapper;
    private final Extractor extractor;
    @Override
    public User createUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new DataIntegrityViolationException("Email exist!");
        }
        User user = User.builder()
                .displayName(userDto.getDisplayName())
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

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void followBook(Integer bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()->new RuntimeException("Book not found"));
        User user = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()->new RuntimeException("User not found"));
        List<User> users = book.getUsers_follow();
        users.add(user);
        book.setUsers_follow(users);
        bookRepository.save(book);
    }

    @Override
    public List<BookResponse> getFollowBook() {
        User user = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()->new RuntimeException("User not found"));
        List<Book> books = user.getBooks();
        return books.stream().map(
                mappers -> modelMapper.map(mappers,BookResponse.class)
        ).collect(Collectors.toList());
    }
}
