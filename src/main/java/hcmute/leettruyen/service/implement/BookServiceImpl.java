package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.component.Extractor;
import hcmute.leettruyen.dto.BookDto;
import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.Genre;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.repository.BookRepository;
import hcmute.leettruyen.repository.GenreRepository;
import hcmute.leettruyen.repository.UserRepository;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.response.ChapterResponse;
import hcmute.leettruyen.service.IBookService;
import hcmute.leettruyen.service.IChapterService;
import hcmute.leettruyen.service.IPurchasedService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final Extractor extractor;
    private final FirebaseStorageService firebaseStorageService;
    private final IChapterService chapterService;
    private final IPurchasedService purchasedService;

    @Override
    public Page<BookResponse> getAllBook(PageRequest pageRequest) {
        return bookRepository.findAll(pageRequest)
                .map(book -> {
                    BookResponse bookResponse = new BookResponse();
                    modelMapper.map(book, bookResponse);
                    bookResponse.setCreatedAt(book.getCreatedAt());
                    bookResponse.setUpdatedAt(book.getUpdatedAt());
                    return bookResponse;
                });
    }

    @Override
    public List<BookResponse> getAllBook() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, BookResponse.class))
                .toList();
    }

    @Override
    public BookResponse createBook(BookDto bookDto) throws Exception {
        List<Genre> genres = genreRepository.findAllById(bookDto.getGenresDto());
        User founduser = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()-> new Exception("Cannot find user"));
        Book book = new Book();
        modelMapper.typeMap(BookDto.class, Book.class)
                .addMappings(mapper -> mapper.skip(Book::setId));
        modelMapper.map(bookDto, book);
        book.setGenres(genres);
        book.setPublicDate(LocalDateTime.now());
        book.setUserOwn(founduser);
        book.setActive(true);
        book.setViews(0);
        book.setBuys(0);
        bookRepository.save(book);
        return modelMapper.map(book, BookResponse.class);
    }
    @PreAuthorize("@bookServiceImpl.getEmailByBook(#id) == authentication.principal.username")
    @Override
    public BookResponse updateBook(Integer id, BookDto bookDto) throws Exception {
        Book foundBook = bookRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find book"));
        if (bookDto.getCoverImage() != null && !bookDto.getCoverImage().equals(foundBook.getCoverImage())){
            if (foundBook.getCoverImage() != null ) {
                if(foundBook.getCoverImage().startsWith("https://firebasestorage.googleapis.com")){

                    String url = foundBook.getCoverImage();
                    URI uri = new URI(url);
                    String path = uri.getPath();

                    String[] parts = path.split("/");
                    String folder = parts[6];
                    String file_name = parts[7];


                    firebaseStorageService.deleteFile(folder, file_name);
                }
            }
            foundBook.setCoverImage(bookDto.getCoverImage());
        }
        List<Genre> genres = genreRepository.findAllById(bookDto.getGenresDto());
        foundBook.setTitle(bookDto.getTitle());
        foundBook.setDescription(bookDto.getDescription());
        foundBook.setGenres(genres);
        bookRepository.save(foundBook);
        return modelMapper.map(foundBook,BookResponse.class);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void hideBook(Integer id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cannot find book"));
        book.setActive(false);
        bookRepository.save(book);
    }

    @Override
    public BookResponse getBookById(Integer id) throws Exception {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find book"));
        return modelMapper.map(book,BookResponse.class);
    }

    @Override
    public List<BookResponse> getBooksSortByDate(Integer num){
        List<Book> books = bookRepository.findTopNByOrderByPublicDateDesc(num);
        return books.stream()
                .filter(Book::getActive)
                .map(book -> modelMapper.map(book,BookResponse.class))
                .toList();
    }
    @Override
    public String getEmailByBook(Integer id) throws Exception {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find book"));
        return book.getUserOwn().getEmail();
    }

    @Override
    public List<BookResponse> getBookByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Cannot find user"));
        List<Book> books = user.getOwn();
        return books.stream()
                .map(book -> modelMapper.map(book,BookResponse.class))
                .toList();
    }

    @Override
    public List<BookResponse> searchBook(String keyword) {
        List<Book> books = bookRepository.findByTitleContaining(keyword);
        return books.stream()
                .filter(Book::getActive)
                .map(book -> modelMapper.map(book,BookResponse.class))
                .toList();
    }

    @Override
    public List<BookResponse> advancedSearch(String title, List<Integer> genres) {
        if(title.isEmpty() && genres.isEmpty()){
            return getAllBook();
        }
        List<Genre> genreList = genreRepository.findAllById(genres);
        if(title.isEmpty() && !genres.isEmpty()){
            return bookRepository.findAll()
                    .stream()
                    .filter(book -> new HashSet<>(book.getGenres()).containsAll(genreList) && book.getActive())
                    .map(book -> modelMapper.map(book,BookResponse.class))
                    .toList();
        }
        List<Book> books = bookRepository.findByTitleContaining(title);
        return books.stream()
                .filter(book -> new HashSet<>(book.getGenres()).containsAll(genreList) && book.getActive())
                .map(book -> modelMapper.map(book,BookResponse.class))
                .toList();
    }

    @Override
    public List<BookResponse> getBestRateBook() {
        List<Book> books = bookRepository.findByOrderByAvgRatingDesc();
        return books.stream()
                .filter(Book::getActive)
                .map(book -> modelMapper.map(book,BookResponse.class))
                .toList();
    }

    @Override
    public List<ChapterResponse> getChapterBoughtByBook(Integer bookId) throws Exception {
        return purchasedService.findByCrtUser()
                .stream()
                .map(purchasedHistory -> purchasedHistory.getChapter().getBook().getId().equals(bookId) ?
                        modelMapper.map(purchasedHistory.getChapter(),ChapterResponse.class) : null)
                .collect(Collectors.toList());
    }
    @Override
    public List<ChapterResponse> getChapterByBook(Integer bookId) throws Exception {
        Book foundBook = bookRepository.findById(bookId)
                .orElseThrow(()-> new Exception("Cannot find book"));
        if(extractor.getUserIdFromToken()==null){
            return chapterService.chapterByBook(bookId).stream()
                    .filter(ChapterResponse::getActive)
                    .collect(Collectors.toList());
        }
        if(foundBook.getUserOwn().getId().equals(extractor.getUserIdFromToken())){
            return chapterService.chapterByBook(bookId);
        }
        List<ChapterResponse> chapterResponses = chapterService.chapterByBook(bookId);
        List<ChapterResponse> chapterOwn = getChapterBoughtByBook(bookId);
        chapterResponses.forEach(chapterResponse ->
                chapterResponse.setBought(chapterOwn.contains(chapterResponse)));
        List<ChapterResponse> chapterResponseList = chapterResponses.stream()
                .filter(ChapterResponse::getActive)
                .toList();
        return chapterResponseList
                .stream()
                .sorted(Comparator.comparing(ChapterResponse::getIndex))
                .toList();
    }

    @Override
    public List<BookResponse> getMostViewBook() {
        List<Book> books = bookRepository.findByOrderByViewsDesc();
        return books.stream()
                .filter(Book::getActive)
                .map(book -> modelMapper.map(book,BookResponse.class))
                .toList();
    }

    @Override
    public List<BookResponse> getMostFollowBook() {
        List<Book> books = bookRepository.findAll();
        books.sort(Comparator.comparingInt(book -> book.getUsers_follow().size()));
        return books.stream()
                .filter(Book::getActive)
                .map(book -> modelMapper.map(book,BookResponse.class))
                .toList();
    }

    @Override
    public List<BookResponse> getMostBuyBook() {
        List<Book> books = bookRepository.findByOrderByBuysDesc();
        return books.stream()
                .filter(Book::getActive)
                .map(book -> modelMapper.map(book,BookResponse.class))
                .toList();
    }
}
