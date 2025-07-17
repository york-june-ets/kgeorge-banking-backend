package solutions.york.bankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solutions.york.bankingbackend.models.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}