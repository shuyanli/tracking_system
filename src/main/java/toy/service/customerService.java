package toy.service;

import toy.domain.Customer;

import java.util.List;

public interface customerService {
    Customer saveCustomer(Customer customer);

    List<Customer> saveCustomers(List<Customer> customers);

    void deleteAll();

    List<Customer> findALl();

    List<Customer> findCustomerByLastName(String lastName); //命名略有不同

    List<Customer> findCustomerByPointOrderByLastName(int point);
}
