package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.dto.ChapterDto;
import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.repository.BookRepository;
import hcmute.leettruyen.repository.ChapterRepository;
import hcmute.leettruyen.response.ChapterResponse;
import hcmute.leettruyen.service.IChapterService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChapterServiceImpl implements IChapterService {
    private final ChapterRepository chapterRepository;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    @Override
    public ChapterResponse createChapter(ChapterDto chapterDto) throws Exception {
        Book foundBook = bookRepository.findById(chapterDto.getBook())
                .orElseThrow(()-> new Exception("Cannot find book"));
        Chapter chapter = new Chapter();
        modelMapper.typeMap(ChapterDto.class, Chapter.class)
                .addMappings(mapper -> mapper.skip(Chapter::setId));
        modelMapper.map(chapterDto, chapter);
        chapter.setBook(foundBook);
        chapter.setActive(true);
        chapter.setView(0);
        chapter.setBuy(0);
        chapterRepository.save(chapter);
        foundBook.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(foundBook);
        return modelMapper.map(chapter,ChapterResponse.class);
    }

    @Override
    public ChapterResponse getChapterById(Integer id) throws Exception {
        Chapter foundChapter = chapterRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find chapter"));
        return modelMapper.map(foundChapter, ChapterResponse.class);
    }

    @Override
    public void deleteChapter(Integer id) throws Exception {
        Chapter foundChapter = chapterRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find chapter"));
        chapterRepository.delete(foundChapter);
    }

    @Override
    public void increaseView(Integer id) throws Exception {
        Chapter foundChapter = chapterRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find chapter"));
        foundChapter.setView(foundChapter.getView()+1);
        chapterRepository.save(foundChapter);
        Book foundBook = foundChapter.getBook();
        foundBook.updateViews();
        bookRepository.save(foundBook);
    }

    @Override
    public ChapterResponse updateChapter(Integer id, ChapterDto chapterDto) throws Exception {
        Book foundBook = bookRepository.findById(chapterDto.getBook())
                .orElseThrow(()-> new Exception("Cannot find book"));
        Chapter foundChapter = chapterRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find chapter"));
        modelMapper.map(chapterDto,foundChapter);
        foundChapter.setBook(foundBook);
        chapterRepository.save(foundChapter);
        return modelMapper.map(foundChapter, ChapterResponse.class);
    }

    @Override
    public List<ChapterResponse> chapterByBook(Integer id) throws Exception {
        Book foundBook = bookRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find book"));
        List<Chapter> chapters = chapterRepository.findAllByBook(foundBook);
        List<Chapter> sortedChapters = chapters.stream()
                .sorted(Comparator.comparingInt(Chapter::getIndex))
                .toList();
        return sortedChapters.stream()
                .map(chapter -> modelMapper.map(chapter,ChapterResponse.class))
                .collect(Collectors.toList());
    }
}
