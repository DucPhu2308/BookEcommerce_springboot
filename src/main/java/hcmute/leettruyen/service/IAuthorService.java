package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.AuthorDto;
import hcmute.leettruyen.response.AuthorResponse;
import hcmute.leettruyen.response.BookResponse;

import java.util.List;

public interface IAuthorService {
    List<AuthorResponse> getAllAuthor();
    AuthorResponse createAuthor(AuthorDto authorDto);
    AuthorResponse updateAuthor(Integer id,AuthorDto authorDto) throws Exception;
    void deleteAuthor(Integer id);
    List<BookResponse> booksByAuthor(Integer id);
}
