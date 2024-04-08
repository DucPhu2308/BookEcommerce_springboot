package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.dto.BookDto;
import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.repository.BookRepository;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.service.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    private final BookRepository bookRepository;

    @Override
    public BookResponse createBook(BookDto bookDto) {
        return null;
    }

    @Override
    public BookResponse updateBook(Integer id, BookDto bookDto) {
        return null;
    }

    @Override
    public void deleteBook(Integer id) {

    }

    @Override
    public void hideBook(Integer id) {

    }
}
