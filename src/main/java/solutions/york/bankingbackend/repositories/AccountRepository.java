package solutions.york.bankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solutions.york.bankingbackend.models.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCustomerId(Long customerId);
}