package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.component.Extractor;
import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.BookHistory;
import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.repository.BookHistoryRepository;
import hcmute.leettruyen.repository.ChapterRepository;
import hcmute.leettruyen.repository.UserRepository;
import hcmute.leettruyen.response.BookHistoryResponse1;
import hcmute.leettruyen.service.IBookHistoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookHistoryServiceImpl implements IBookHistoryService {
    private final BookHistoryRepository bookHistoryRepository;
    private final UserRepository userRepository;
    private final ChapterRepository chapterRepository;
    private final Extractor extractor;
    private final ModelMapper modelMapper;

    @Override
    public List<BookHistoryResponse1> findBookHistoryByCrtUser() throws Exception {
        User founduser = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()-> new Exception("Cannot find user"));
        List<BookHistory> bookHistories = bookHistoryRepository.findByUserReadIdOrderByViewedAtDesc(founduser.getId());
        if(bookHistories != null){
            return bookHistories.stream()
                    .map(mapper -> modelMapper.map(mapper, BookHistoryResponse1.class))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void createBookHistory(Integer chapterId) throws Exception {
        User founduser = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()-> new Exception("Cannot find user"));
        Chapter foundChapter = chapterRepository.findById(chapterId)
                .orElseThrow(()-> new Exception("Cannot find chapter"));
        Book foundBook = foundChapter.getBook();
        BookHistory foundBookHistory = bookHistoryRepository.findByUserReadIdAndBookReadId(founduser.getId(), foundBook.getId());
        if (foundBookHistory != null) {
            foundBookHistory.setViewedAt(LocalDateTime.now());
            bookHistoryRepository.save(foundBookHistory);
        }else {
            BookHistory bookHistory = new BookHistory();
            bookHistory.setUserRead(founduser);
            bookHistory.setBookRead(foundBook);
            bookHistory.setViewedAt(LocalDateTime.now());
            bookHistoryRepository.save(bookHistory);
        }
    }
}
