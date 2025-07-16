package solutions.york.bankingbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import solutions.york.bankingbackend.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
