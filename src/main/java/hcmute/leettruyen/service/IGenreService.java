package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.GenreDto;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.response.GenreResponse;

import java.util.List;

public interface IGenreService {
    GenreResponse createGenre(GenreDto genreDto);
    List<GenreResponse> getAllGenre();
    GenreResponse updateGenre(Integer id, GenreDto genreDto) throws Exception;
    void deleteGenre(Integer id);
    List<BookResponse> booksByGenre(Integer id);
}
