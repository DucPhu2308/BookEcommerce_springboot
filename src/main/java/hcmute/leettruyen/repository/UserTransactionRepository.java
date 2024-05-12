package hcmute.leettruyen.repository;

import hcmute.leettruyen.entity.User;
import hcmute.leettruyen.entity.UserTransaction;
import hcmute.leettruyen.response.UserTransactionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction,Integer> {
    List<UserTransaction> findAllByUser(User user);
}
