package solutions.york.bankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solutions.york.bankingbackend.models.Transfer;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
