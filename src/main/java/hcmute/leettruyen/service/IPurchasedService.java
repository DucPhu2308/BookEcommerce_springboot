package hcmute.leettruyen.service;

import hcmute.leettruyen.entity.PurchasedHistory;
import java.util.List;
public interface IPurchasedService {
    List<PurchasedHistory> findByCrtUser() throws Exception;
    boolean checkPurchased(Integer bookId) throws Exception;
}
