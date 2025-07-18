package solutions.york.bankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solutions.york.bankingbackend.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
