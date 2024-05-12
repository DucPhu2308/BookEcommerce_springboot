package hcmute.leettruyen.service;

import hcmute.leettruyen.dto.UserTransactionDto;
import hcmute.leettruyen.entity.UserTransaction;
import hcmute.leettruyen.response.UserTransactionResponse;

import java.util.List;

public interface IUserTransactionService {
    UserTransaction save(UserTransactionDto userTransactionDto);
    List<UserTransactionResponse> getAllTransactionByUser(int userId);
}
