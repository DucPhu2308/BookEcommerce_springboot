package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.BookDto;
import hcmute.leettruyen.response.BookResponse;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IBookService {
    Page<BookResponse> getAllBook(PageRequest pageRequest);
    BookResponse createBook(BookDto bookDto) throws Exception;
    BookResponse updateBook(Integer id, BookDto bookDto) throws Exception;
    void deleteBook(Integer id);
    void hideBook(Integer id);
    BookResponse getBookById(Integer id) throws Exception;
    public List<BookResponse> getBooksSortByDate(Integer num);
}
