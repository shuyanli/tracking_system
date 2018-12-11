package toy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toy.domain.Customer;
import toy.domain.CustomerRepository;
import toy.service.customerService;

import java.util.List;


@Service
public class customerServiceImpl implements customerService {


    CustomerRepository customerRepository;

    @Autowired
    public customerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> saveCustomers(List<Customer> customers) {
        return customerRepository.save(customers);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

    @Override
    public List<Customer> findALl() {
        return customerRepository.findAll();
    }

    @Override //todo
    public List<Customer> findCustomerByLastName(String lastName) {
        return customerRepository.findCustomerByLastName(lastName);
    }

    @Override
    public List<Customer> findCustomerByPointOrderByLastName(int point) {
        return customerRepository.findCustomerByPointOrderByLastName(point);
    }

}
