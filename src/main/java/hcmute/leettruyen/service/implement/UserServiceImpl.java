package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.component.Extractor;
import hcmute.leettruyen.component.JwtTokenUtil;
import hcmute.leettruyen.dto.UpdateInfoDto;
import hcmute.leettruyen.dto.UserDto;
import hcmute.leettruyen.entity.*;
import hcmute.leettruyen.repository.*;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.response.ParagraphResponse;
import hcmute.leettruyen.response.UserResponse;
import hcmute.leettruyen.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final ParagraphRepository paragraphRepository;
    private final PurchasedHistoryRepository purchasedHistoryRepository;
    private final ChapterRepository chapterRepository;
    @Override
    public User createUser(UserDto userDto) {
        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new DataIntegrityViolationException("Email exist!");
        }
        User user = User.builder()
                .displayName(userDto.getDisplayName())
                .email(userDto.getEmail())
                .active(true)
                .build();
        Role role = roleRepository.findById(2)
                .orElseThrow(()-> new RuntimeException("Role not found"));
        user.setRoles(List.of(role));
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public UserResponse updateUserInfo(UpdateInfoDto userDto) {
        User user = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()->new RuntimeException("User not found"));
        user.setDisplayName(userDto.getDisplayName());
        user.setIntroduction(userDto.getIntroduction());
        user.setCoin(userDto.getCoin());
        userRepository.save(user);
        return modelMapper.map(user,UserResponse.class);
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
        if(users.contains(user)){
            users.remove(user);
        }else {
            users.add(user);
        }
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

    @Override
    public void markParagraph(Integer paragraphId) {
        Paragraph paragraph = paragraphRepository.findById(paragraphId)
                .orElseThrow(()->new RuntimeException("Paragraph not found"));
        User user = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()->new RuntimeException("User not found"));
        List<Paragraph> paragraphs = user.getBookmarks();
        if(paragraphs.contains(paragraph)){
            paragraphs.remove(paragraph);
        }else {
            paragraphs.add(paragraph);
        }
        user.setBookmarks(paragraphs);
        userRepository.save(user);
    }

    @Override
    public List<ParagraphResponse> getMarkParagraph() {
        User user = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()->new RuntimeException("User not found"));
        List<Paragraph> paragraphs = user.getBookmarks();
        return paragraphs.stream().map(
                mappers -> modelMapper.map(mappers,ParagraphResponse.class)
        ).collect(Collectors.toList());
    }

    @Override
    public void followUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User not found"));
        User currentUser = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()->new RuntimeException("User not found"));
        List<User> users = currentUser.getSubscribing();
        if(users.contains(user)){
            users.remove(user);
        }else {
            users.add(user);
        }
        currentUser.setSubscribing(users);
        userRepository.save(currentUser);
    }

    @Override
    public List<UserResponse> getFollowingUser() {
        User user = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()->new RuntimeException("User not found"));
        List<User> users = user.getSubscribing();
        return users.stream().map(
                mappers -> modelMapper.map(mappers,UserResponse.class)
        ).collect(Collectors.toList());
    }

    @Override
    public List<UserResponse> getFollowedUser() {
        User user = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()->new RuntimeException("User not found"));
        List<User> users = user.getSubscribed();
        return users.stream().map(
                mappers -> modelMapper.map(mappers,UserResponse.class)
        ).collect(Collectors.toList());
    }

    @Override
    public void changePassword(String password) {
        User user = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()->new RuntimeException("User not found"));
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
    @Override
    public void buyChapter(Integer chapterId) throws Exception {
        User current = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()-> new RuntimeException("Cannot find user"));
        Chapter foundChapter = chapterRepository.findById(chapterId)
                .orElseThrow(()-> new RuntimeException("Cannot find chapter"));
        if(purchasedHistoryRepository.findByUserAndChapter(current,foundChapter) != null){
            throw new Exception("Chapter already purchased");
        }
        int coin = current.getCoin();
        if(coin >= foundChapter.getPrice()){
            coin -= foundChapter.getPrice();
            current.setCoin(coin);
            userRepository.save(current);
            PurchasedHistory purchasedHistory = new PurchasedHistory();
            purchasedHistory.setUser(current);
            purchasedHistory.setChapter(foundChapter);
            purchasedHistory.setCoin(foundChapter.getPrice());
            purchasedHistory.setDateTime(LocalDateTime.now());
            purchasedHistoryRepository.save(purchasedHistory);
        }else {
            throw new Exception("Not enough coin");
        }
    }
}
