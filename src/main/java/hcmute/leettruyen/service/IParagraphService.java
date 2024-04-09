package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.ParagraphDto;
import hcmute.leettruyen.response.ParagraphResponse;

import java.util.List;

public interface IParagraphService {
    ParagraphResponse createParagraph(ParagraphDto paragraphDto) throws Exception;
    ParagraphResponse updateParagraph(Integer id, ParagraphDto paragraphDto) throws Exception;
    void deleteParagraph(Integer id);
    List<ParagraphResponse> getParagraphByChapter(Integer id) throws Exception;
}
