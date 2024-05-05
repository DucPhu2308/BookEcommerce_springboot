package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.BookDto;
import hcmute.leettruyen.response.BookResponse;

import java.util.List;

import hcmute.leettruyen.response.ChapterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IBookService {
    Page<BookResponse> getAllBook(PageRequest pageRequest);
    List<BookResponse> getAllBook();
    BookResponse createBook(BookDto bookDto) throws Exception;
    BookResponse updateBook(Integer id, BookDto bookDto) throws Exception;
    void deleteBook(Integer id);
    void hideBook(Integer id);
    BookResponse getBookById(Integer id) throws Exception;
    List<BookResponse> getBooksSortByDate(Integer num);
    String getEmailByBook(Integer id) throws Exception;
    List<BookResponse> getBookByUser(Integer userId);
    List<BookResponse> searchBook(String keyword);
    List<BookResponse> advancedSearch(String title, List<Integer> genres);
    List<BookResponse> getBestRateBook();
    List<ChapterResponse> getChapterBoughtByBook(Integer bookId) throws Exception;
    List<ChapterResponse> getChapterByBook(Integer bookId) throws Exception;
    List<BookResponse> getMostViewBook();
    List<BookResponse> getMostFollowBook();
}
