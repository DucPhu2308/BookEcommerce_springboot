package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.ChapterDto;
import hcmute.leettruyen.response.ChapterResponse;

import java.util.List;

public interface IChapterService {
    ChapterResponse createChapter(ChapterDto chapterDto) throws Exception;
    ChapterResponse updateChapter(Integer id, ChapterDto chapterDto) throws Exception;
    List<ChapterResponse> chapterByBook(Integer id) throws Exception;
}