package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.dto.BookDto;
import hcmute.leettruyen.entity.Author;
import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.Genre;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.repository.AuthorRepository;
import hcmute.leettruyen.repository.BookRepository;
import hcmute.leettruyen.repository.GenreRepository;
import hcmute.leettruyen.repository.UserRepository;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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
    public BookResponse createBook(BookDto bookDto) throws Exception {
        List<Genre> genres = genreRepository.findAllById(bookDto.getGenresDto());
        List<Author> authors = authorRepository.findAllById(bookDto.getAuthorsDto());
        User founduser = userRepository.findById(bookDto.getUserOwn())
                .orElseThrow(()-> new Exception("Cannot find user"));
        Book book = new Book();
        modelMapper.typeMap(BookDto.class, Book.class)
                .addMappings(mapper -> mapper.skip(Book::setId));
        modelMapper.map(bookDto, book);
        book.setAuthors(authors);
        book.setGenres(genres);
        book.setPublicDate(LocalDateTime.now());
        book.setUserOwn(founduser);
        bookRepository.save(book);
        return modelMapper.map(book, BookResponse.class);
    }

    @Override
    @PreAuthorize("bookRepository.findById(#id).get().userOwn.email == principal.username")
    public BookResponse updateBook(Integer id, BookDto bookDto) throws Exception {
        Book foundBook = bookRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find book"));
        List<Genre> genres = genreRepository.findAllById(bookDto.getGenresDto());
        List<Author> authors = authorRepository.findAllById(bookDto.getAuthorsDto());
        foundBook.setTitle(bookDto.getTitle());
        foundBook.setDescription(bookDto.getDescription());
        foundBook.setCoverImage(bookDto.getCoverImage());
        foundBook.setGenres(genres);
        foundBook.setAuthors(authors);
        bookRepository.save(foundBook);
        return modelMapper.map(foundBook,BookResponse.class);
    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void hideBook(Integer id) {

    }

    @Override
    public BookResponse getBookById(Integer id) throws Exception {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find book"));
        return modelMapper.map(book,BookResponse.class);
    }
}
