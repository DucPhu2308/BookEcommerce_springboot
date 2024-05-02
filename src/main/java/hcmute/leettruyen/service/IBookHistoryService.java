package hcmute.leettruyen.service;

import hcmute.leettruyen.response.BookHistoryResponse;

import java.util.List;

public interface IBookHistoryService {
    List<BookHistoryResponse> findBookHistoryByCrtUser() throws Exception;
    void createBookHistory(Integer chapterId) throws Exception;
}
