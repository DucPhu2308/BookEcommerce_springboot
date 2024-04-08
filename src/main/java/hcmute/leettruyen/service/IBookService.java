package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.BookDto;
import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.response.BookResponse;

public interface IBookService {
    BookResponse createBook(BookDto bookDto);
    BookResponse updateBook(Integer id, BookDto bookDto);
    void deleteBook(Integer id);
    void hideBook(Integer id);
}
