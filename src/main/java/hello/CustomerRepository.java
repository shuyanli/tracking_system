package hello;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    List<Customer> findCustomerByLastName(String lastName); //命名略有不同
}
