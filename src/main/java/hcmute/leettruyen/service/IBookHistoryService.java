package hcmute.leettruyen.service;

import hcmute.leettruyen.response.BookHistoryResponse1;

import java.util.List;

public interface IBookHistoryService {
    List<BookHistoryResponse1> findBookHistoryByCrtUser() throws Exception;
    void createBookHistory(Integer chapterId) throws Exception;
}
