package solutions.york.bankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import solutions.york.bankingbackend.models.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    public List<Transaction> findByAccount_AccountNumber(Long accountNumber);

//    @Query("SELECT t FROM Transaction t WHERE " +
//            "(:accountNumber IS NULL OR t.account.accountNumber = :accountNumber) AND " +
//            "(:type IS NULL OR t.transactionType = :type) AND " +
//            "(:fromDate IS NULL OR DATE(t.timestamp) >= :fromDate) AND " +
//            "(:toDate IS NULL OR DATE(t.timestamp) <= :toDate) AND " +
//            "(:minAmount IS NULL OR t.amount >= :minAmount) AND " +
//            "(:maxAmount IS NULL OR t.amount <= :maxAmount)")
//    public List<Transaction> findWithFilters(
//            @Param("accountNumber") Long accountNumber,
//            @Param("type") Transaction.Type type,
//            @Param("fromDate") java.time.LocalDate fromDate,
//            @Param("toDate") java.time.LocalDate toDate,
//            @Param("minAmount") Double minAmount,
//            @Param("maxAmount") Double maxAmount
//    );

}
