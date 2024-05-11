package hcmute.leettruyen.service.implement;

import hcmute.leettruyen.component.Extractor;
import hcmute.leettruyen.entity.Book;
import hcmute.leettruyen.entity.PurchasedHistory;
import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.repository.BookRepository;
import hcmute.leettruyen.repository.PurchasedHistoryRepository;
import hcmute.leettruyen.repository.UserRepository;
import hcmute.leettruyen.service.IPurchasedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PurchasedImpl implements IPurchasedService {
    private final PurchasedHistoryRepository purchasedHistoryRepository;
    private final BookRepository bookRepository;
    private final Extractor extractor;
    private final UserRepository userRepository;
    @Override
    public List<PurchasedHistory> findByCrtUser() throws Exception {
        User user = userRepository.findById(extractor.getUserIdFromToken())
                .orElseThrow(()-> new Exception("Cannot find user"));
        return purchasedHistoryRepository.findByUser(user);
    }

    @Override
    public boolean checkPurchased(Integer bookId) throws Exception {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(()-> new Exception("Cannot find book"));
        List<PurchasedHistory> purchasedHistories = findByCrtUser();
        for (PurchasedHistory purchasedHistory : purchasedHistories){
            if(purchasedHistory.getChapter().getBook().equals(book)){
                return true;
            }
        }
        return false;
    }
}
