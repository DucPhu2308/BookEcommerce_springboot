package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.BookDto;
import hcmute.leettruyen.entity.Book;

public interface IBookService {
    Book createBook(BookDto bookDto);
}
