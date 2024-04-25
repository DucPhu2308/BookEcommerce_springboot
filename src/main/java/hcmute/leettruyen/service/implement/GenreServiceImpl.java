package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.dto.GenreDto;
import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.Genre;
import hcmute.leettruyen.repository.BookRepository;
import hcmute.leettruyen.repository.GenreRepository;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.response.GenreResponse;
import hcmute.leettruyen.service.IGenreService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements IGenreService {
    private final GenreRepository genreRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    @Override
    public GenreResponse createGenre(GenreDto genreDto) {
        modelMapper.typeMap(GenreDto.class, Genre.class)
                .addMappings(mapper -> mapper.skip(Genre::setId));
        Genre genre = new Genre();
        modelMapper.map(genreDto,genre);
        genreRepository.save(genre);
        return modelMapper.map(genre,GenreResponse.class);
    }

    @Override
    public List<GenreResponse> getAllGenre() {
        List<Genre> genres = genreRepository.findAll();
        return genres
                .stream()
                .map(genre -> modelMapper.map(genre,GenreResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public GenreResponse updateGenre(Integer id, GenreDto genreDto) throws Exception {
        Genre foundGenre = genreRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot found Genre"));
//        foundGenre.setName(genreDto.getName());
//        foundGenre.setColor(genreDto.getColor());
        modelMapper.map(genreDto,foundGenre);
        genreRepository.save(foundGenre);
        return modelMapper.map(foundGenre,GenreResponse.class);
    }

    @Override
    public void deleteGenre(Integer id) {
        genreRepository.deleteById(id);
    }

    @Override
    public List<BookResponse> booksByGenre(Integer id) throws Exception {
        Genre foundGenre = genreRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find genre"));
        List<Book> books = bookRepository.findAllByGenresContains(foundGenre);
        return books.stream().map(book -> modelMapper.map(book,BookResponse.class))
                .collect(Collectors.toList());
    }
}
