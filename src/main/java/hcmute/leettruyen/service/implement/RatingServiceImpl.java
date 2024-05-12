package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.component.Extractor;
import hcmute.leettruyen.dto.RatingDto;
import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.Rating;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.repository.BookRepository;
import hcmute.leettruyen.repository.RatingRepository;
import hcmute.leettruyen.repository.UserRepository;
import hcmute.leettruyen.response.RatingResponse;
import hcmute.leettruyen.service.IPurchasedService;
import hcmute.leettruyen.service.IRatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements IRatingService {
    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final IPurchasedService purchasedService;
    private final Extractor extractor;
    @Override
    public RatingResponse createRating(RatingDto ratingDto) throws Exception {
        Book foundBook = bookRepository.findById(ratingDto.getBook())
                .orElseThrow(()-> new Exception("Cannot find book"));
        User foundUser = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()-> new Exception("Cannot find user"));
        if(!purchasedService.checkPurchased(foundBook.getId())){
            throw new Exception("User not purchased");
        }
        if(ratingRepository.existsByBookAndUser(foundBook,foundUser)){
            throw new Exception("User rated");
        }
        Rating rating = Rating.builder()
                .star(ratingDto.getStar())
                .content(ratingDto.getContent())
                .book(foundBook)
                .user(foundUser)
                .build();
        ratingRepository.save(rating);
        setRating(ratingDto.getBook());
        return modelMapper.map(rating,RatingResponse.class);
    }

    @Override
    public RatingResponse updateRating(Integer id, RatingDto ratingDto) throws Exception {
        Rating foundRating = ratingRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find user"));
        foundRating.setStar(ratingDto.getStar());
        foundRating.setContent(ratingDto.getContent());
        ratingRepository.save(foundRating);
        setRating(ratingDto.getBook());
        return modelMapper.map(foundRating,RatingResponse.class);
    }
    @Override
    public void deleteRating(Integer id) throws Exception {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find Rating"));
        Integer bookId = rating.getBook().getId();
        ratingRepository.deleteById(id);
        setRating(bookId);
    }
    @Override
    public List<RatingResponse> getRatingByBook(Integer id) throws Exception {
        Book foundBook = bookRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find book"));
        List<Rating> ratings = ratingRepository.findAllByBook(foundBook);
        return ratings.stream().map(rating -> modelMapper.map(rating,RatingResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public void setRating(Integer id) throws Exception {
        Book book = bookRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find book"));
        Float averageRating = bookRepository.findAverageRatingByBook(book);
        book.setAvgRating(averageRating);
        bookRepository.save(book);
    }
}
