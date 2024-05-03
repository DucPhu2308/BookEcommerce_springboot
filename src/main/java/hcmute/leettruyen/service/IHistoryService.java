package hcmute.leettruyen.service;

import hcmute.leettruyen.response.BookHistoryResponse;

import java.util.List;

public interface IHistoryService {
    List<BookHistoryResponse> findBookHistoryByCrtUser() throws Exception;
    void createBookHistory(Integer chapterId) throws Exception;
}
