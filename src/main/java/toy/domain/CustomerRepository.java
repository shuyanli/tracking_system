package toy.domain;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findCustomerByLastName(String lastName); //命名略有不同

    List<Customer> findCustomerByPointOrderByLastName(int point);



}
