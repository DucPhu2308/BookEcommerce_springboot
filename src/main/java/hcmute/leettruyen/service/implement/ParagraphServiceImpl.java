package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.dto.ParagraphDto;
import hcmute.leettruyen.entity.Chapter;
import hcmute.leettruyen.entity.Paragraph;
import hcmute.leettruyen.repository.ChapterRepository;
import hcmute.leettruyen.repository.ParagraphRepository;
import hcmute.leettruyen.response.ParagraphResponse;
import hcmute.leettruyen.service.IParagraphService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParagraphServiceImpl implements IParagraphService {
    private final ParagraphRepository paragraphRepository;
    private final ChapterRepository chapterRepository;
    private final ModelMapper modelMapper;
    @Override
    public ParagraphResponse createParagraph(ParagraphDto paragraphDto) throws Exception {
        Chapter foundChapter = chapterRepository.findById(paragraphDto.getChapter())
                .orElseThrow(()-> new Exception("Cannot find chapter"));
        modelMapper.typeMap(ParagraphDto.class, Paragraph.class)
                .addMappings(mapper -> mapper.skip(Paragraph::setId));
        Paragraph paragraph = new Paragraph();
        modelMapper.map(paragraphDto, paragraph);
        paragraph.setChapter(foundChapter);
        paragraphRepository.save(paragraph);
        return modelMapper.map(paragraph,ParagraphResponse.class);
    }

    @Override
    public ParagraphResponse updateParagraph(Integer id, ParagraphDto paragraphDto) throws Exception {
        Paragraph foundParagraph = paragraphRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find Paragraph"));
        Chapter foundChapter = chapterRepository.findById(paragraphDto.getChapter())
                .orElseThrow(()-> new Exception("Cannot find chapter"));
        modelMapper.map(paragraphDto, foundParagraph);
        foundParagraph.setChapter(foundChapter);
        paragraphRepository.save(foundParagraph);
        return modelMapper.map(foundParagraph, ParagraphResponse.class);
    }

    @Override
    public void deleteParagraph(Integer id) {
        paragraphRepository.deleteById(id);
    }
    @Override
    public List<ParagraphResponse> getParagraphByChapter(Integer id) throws Exception {
        Chapter foundChapter = chapterRepository.findById(id)
                .orElseThrow(()-> new Exception("Cannot find Chapter"));
        List<Paragraph> paragraphs = paragraphRepository.findAllByChapter(foundChapter);
        List<Paragraph> sortedParagraphs = paragraphs.stream()
                .sorted(Comparator.comparingInt(Paragraph::getIndex))
                .toList();
        return sortedParagraphs.stream()
                .map(paragraph -> modelMapper.map(paragraph,ParagraphResponse.class))
                .collect(Collectors.toList());
    }
}
