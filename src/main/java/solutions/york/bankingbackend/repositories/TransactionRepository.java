package solutions.york.bankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solutions.york.bankingbackend.models.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findByAccount_AccountNumber(Long accountNumber);

}
