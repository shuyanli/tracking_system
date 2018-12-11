package toy.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import toy.domain.Customer;
import toy.service.impl.customerServiceImpl;

import java.util.List;

@RestController
public class toyApplicationController {
    private customerServiceImpl customerService;
    private static final Logger log = LoggerFactory.getLogger(toyApplicationController.class);

    @Autowired
    public toyApplicationController(customerServiceImpl customerService){
        this.customerService = customerService;
    }


    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    Customer saveCustomer(@RequestBody String firstName,
                          @RequestBody String lastName,
                          @RequestBody int point){
        return customerService.saveCustomer(new Customer(firstName, lastName, point));
        //todo 改成void如何
    }

//    @RequestMapping(value = "/customers", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    List<Customer> saveCustomers(@RequestBody List<Customer> customers){
//        return customerService.saveCustomers(customers);
//    }


    @RequestMapping(value = "/customers", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    void purge(){
        customerService.deleteAll();
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    List<Customer> findAllUserInDatabase(){
        return customerService.findALl();
    }

    @RequestMapping(value = "/customers/{lastname}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    List<Customer> findCustomerByLastName(@PathVariable String lastName){
        List<Customer> res = customerService.findCustomerByLastName(lastName);

        for (Customer customer : res) {
            System.out.println(customer.toString());
        }

        return res;
    }

    @RequestMapping(value = "/customers")
    List<Customer> findCustomerByPoint(@RequestParam(name = "point") int point){
        List<Customer> res = customerService.findCustomerByPointOrderByLastName(point);
        for (Customer customer : res) {
            log.info(customer.toString());
        }
        return res;
    }




}