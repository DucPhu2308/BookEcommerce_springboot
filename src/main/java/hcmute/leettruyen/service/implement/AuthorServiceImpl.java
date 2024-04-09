package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.dto.AuthorDto;
import hcmute.leettruyen.entity.Author;
import hcmute.leettruyen.repository.AuthorRepository;
import hcmute.leettruyen.response.AuthorResponse;
import hcmute.leettruyen.response.BookResponse;
import hcmute.leettruyen.service.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements IAuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<AuthorResponse> getAllAuthor() {
        return authorRepository.findAll()
                .stream().map(author -> modelMapper.map(author,AuthorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponse createAuthor(AuthorDto authorDto) {
        modelMapper.typeMap(AuthorDto.class, Author.class)
                .addMappings(mapper -> mapper.skip(Author::setId));
        Author author = new Author();
        modelMapper.map(authorDto,author);
        authorRepository.save(author);
        return modelMapper.map(author, AuthorResponse.class);
    }

    @Override
    public AuthorResponse updateAuthor(Integer id, AuthorDto authorDto) throws Exception {
        Author foundAuthor = authorRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find author!!"));
        modelMapper.map(authorDto, foundAuthor);
        authorRepository.save(foundAuthor);
        return modelMapper.map(foundAuthor,AuthorResponse.class);
    }

    @Override
    public void deleteAuthor(Integer id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<BookResponse> booksByAuthor(Integer id) {
        return null;
    }
}
