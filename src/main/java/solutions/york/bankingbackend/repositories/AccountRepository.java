package solutions.york.bankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solutions.york.bankingbackend.models.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Optional<Account> findByAccountNumber(Long accountNumber);
    public List<Account> findByCustomerId(Long customerId);
}