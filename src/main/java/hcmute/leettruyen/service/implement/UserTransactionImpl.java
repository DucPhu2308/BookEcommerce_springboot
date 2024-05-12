package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.component.Extractor;
import hcmute.leettruyen.dto.UserTransactionDto;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.entity.UserTransaction;
import hcmute.leettruyen.repository.UserRepository;
import hcmute.leettruyen.repository.UserTransactionRepository;
import hcmute.leettruyen.response.UserTransactionResponse;
import hcmute.leettruyen.service.IUserTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTransactionImpl implements IUserTransactionService {
    private final UserTransactionRepository userTransactionRepository;
    private final ModelMapper modelMapper;
    private final Extractor extractor;
    private final UserRepository userRepository;
    @Override
    public UserTransaction save(UserTransactionDto userTransactionDto) {
        UserTransaction userTransaction = new UserTransaction();
        userTransaction.setCoin(userTransactionDto.getCoin());
        userTransaction.setAmount(userTransactionDto.getAmount());
        userTransaction.setDateTime(LocalDateTime.now());
        userTransaction.setIsDeposit(true);
        userTransaction.setUser(userRepository.findById(extractor.getUserIdFromToken()).get());
        userTransactionRepository.save(userTransaction);
        return modelMapper.map(userTransaction, UserTransaction.class);

    }

    @Override
    public List<UserTransactionResponse> getAllTransactionByUser(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("Cannot find user"));

        List<UserTransaction> userTransactions = userTransactionRepository.findAllByUser(user);
        return userTransactions.stream().map(userTransaction -> modelMapper.map(userTransaction, UserTransactionResponse.class)).toList();
    }


}
