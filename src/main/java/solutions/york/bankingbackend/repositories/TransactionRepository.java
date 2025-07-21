package solutions.york.bankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import solutions.york.bankingbackend.models.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_AccountNumber(Long accountNumber);

    @Query("SELECT t FROM Transaction t WHERE " +
      "(:accountNumber IS NULL OR t.account.accountNumber = :accountNumber) AND " +
      "(:transactionType IS NULL OR t.transactionType = :transactionType) AND " +
      "(:fromDate IS NULL OR t.timestamp >= :fromDate) AND " +
      "(:toDate IS NULL OR t.timestamp <= :toDate) AND " +
      "(:minAmount IS NULL OR t.amount >= :minAmount) AND " +
      "(:maxAmount IS NULL OR t.amount <= :maxAmount)")
    List<Transaction> findTransactionWithFilters(
        @Param("accountNumber") Long accountNumber,
        @Param("transactionType") Transaction.Type transactionType,
        @Param("fromDate") LocalDate fromDate,
        @Param("toDate") LocalDate toDate,
        @Param("minAmount") Double minAmount,
        @Param("maxAmount") Double maxAmount
    );
}
