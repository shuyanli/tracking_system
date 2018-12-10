package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;


@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    //With CommandLineRunner you can perform tasks after all Spring Beans are created
    // and the Application Context has been created.
    @Bean
    public CommandLineRunner myDemo (CustomerRepository repository){
        return (args)->{
            repository.save(new Customer("shuyan","li"));
            repository.save(new Customer("jian","wang"));
            repository.save(new Customer("fei","li"));
            repository.save(new Customer("zifeng","kang"));
            repository.save(new Customer("wanjun","jin"));
            repository.save(new Customer("futai","liu"));
            repository.save(new Customer("yuhua","zhang"));


            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info("");


            if (repository.exists(1)) {
                Customer customer = repository.findOne(1);
                log.info("find by id 1:");
                log.info(customer.toString());
            }

            log.info("Customer found with findByLastName('li'):");
            log.info("--------------------------------------------");
//            repository.findCustomerByLastName("li").forEach(res->{
//                log.info(res.toString());
//            });
//            Pageable pageable = new PageRequest(1, 3);
//            Page<Customer> res = repository.findCustomerByLastName("li", pageable);



        };
    }
}
